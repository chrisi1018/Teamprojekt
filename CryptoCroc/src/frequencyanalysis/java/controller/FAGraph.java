package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

/**
 * Erstellt die Grafik zur Buchstabenverteilung der Haeufigkeitsanalyse
 * 
 * @author zes
 * @version 1.5
 */
public class FAGraph {

	private JPanel graphPanel;
	// Buchstabenverteilungen
	private double[] fLanguage;
	private double[] fActual;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String language;
	// Standard von 20, da groesste Prozentzahl der deutschen Verteilung das E mit
	// 17% ist
	private int max = 20;

	/**
	 * Konstruktor fuer einen FAGraph, erstellt das GraphPanel
	 * 
	 * @param fLanguage Buchstabenverteilung einer uebergebenen Sprache
	 * @param fActual   eigentliche Buchstabenverteilung im Text
	 * @param language  die Sprache mit der der eingegebene Text verglichen werden
	 *                  soll
	 * @param max       die groesste vorkommende Zahl um das Panel daran anzupassen
	 */
	public FAGraph(float[] fLanguage, float[] fActual, String language, int max) throws IOException {
		this.language = language;
		this.fLanguage = new double[fLanguage.length];
		for (int i = 0; i < fLanguage.length; i++) {
			this.fLanguage[i] = Double.parseDouble(Float.toString(fLanguage[i]));
		}
		this.fActual = new double[fActual.length];
		for (int i = 0; i < fActual.length; i++) {
			this.fActual[i] = Double.parseDouble(Float.toString(fActual[i]));
		}
		if (max > this.max) {
			this.max = max + 15;
		}
		createGraphPanel();
	}

	/**
	 * Erstellt das GraphPanel
	 */
	public void createGraphPanel() {
		// zum Erstellen eines DataSets
		DefaultKeyedValues2DDataset m = new DefaultKeyedValues2DDataset();

		for (int i = 0; i < fLanguage.length; i++) {
			// Wert alle nacheinander
			// Reihenschluessel, gleiche gehoeren zur gleichen Series
			// Zeilenschluessel, gleiche gehoeren zum gleichen Buchstaben des Alphabets
			m.addValue(fLanguage[i], language + "e Verteilung", Character.toString(alphabet.charAt(i)));
			m.addValue(fActual[i], "Verteilung im Text", Character.toString(alphabet.charAt(i)));
		}

		// Balkendiagramm mit Eingabeparametern (Titel oben, Titel unten, Y Achse, dataset,
		// Ausrichtung der Balken, Legende erzeugen, Tooltips, URLs)
		JFreeChart barChart = ChartFactory.createBarChart("", "", "", m, PlotOrientation.VERTICAL, false, false, false);

		// Plot der Chart holen und manuell Legende hinzufuegen; nur so koennen
		// Orientierung und Textstil angepasst werden
		CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
		LegendTitle legend = new LegendTitle(cplot);
		Font font = new Font(Font.DIALOG, Font.BOLD, 10);
		legend.setItemFont(font);
		// Legende wird links unten positioniert
		legend.setPosition(RectangleEdge.BOTTOM);
		legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		// Padding zwischen einzelnen Legenden-Items
		legend.setItemLabelPadding(new RectangleInsets(2, 2, 2, 30));
		barChart.addLegend(legend);

		// Hintergrund des Plots (Standard ist grau) auf weiss setzen und y-Achse mit
		// Zahlenwerten ausblenden
		cplot.setBackgroundPaint(Color.white);
		cplot.getRangeAxis().setVisible(false);
		// Abstand zur y-Achse links und rechts
		cplot.getDomainAxis().setLowerMargin(0.01);
		cplot.getDomainAxis().setUpperMargin(0.01);

		// Farben der Bars entsprechend ihrem Reihenschluessel faerben
		((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
		// CryptoCroc gruen
		renderer.setSeriesPaint(0, new Color(74, 115, 14));
		// gelb/orange/gold
		renderer.setSeriesPaint(1, new Color(185, 137, 0));

		// Werte der Haeufigkeitsverteilung oberhalb der Balken fuer beide Serien
		// aktivieren
		CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		renderer.setSeriesItemLabelGenerator(0, generator);
		renderer.setSeriesItemLabelPaint(0, new Color(74, 115, 14));
		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesPositiveItemLabelPosition(0,
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 2));
		renderer.setSeriesItemLabelFont(0, new Font(Font.DIALOG, Font.PLAIN, 10));
		renderer.setSeriesItemLabelGenerator(1, generator);
		renderer.setSeriesItemLabelPaint(1, new Color(185, 137, 0));
		renderer.setSeriesItemLabelsVisible(1, true);
		renderer.setSeriesPositiveItemLabelPosition(1,
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER, TextAnchor.CENTER, -Math.PI / 2));
		renderer.setSeriesItemLabelFont(1, new Font(Font.DIALOG, Font.BOLD, 10));
		// Abstand der Werte-Labels zu den Balken
		renderer.setItemLabelAnchorOffset(15);

		// Schrittgroesse der y-Achse einstellen
		NumberAxis rangeAxis = (NumberAxis) cplot.getRangeAxis();
		rangeAxis.setRange(0, max);
		rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		rangeAxis.setTickUnit(new NumberTickUnit(10.0));

		graphPanel = new JPanel();

		// konvertieren zu ChartPanel, sodass es zum graphPanel hinzugefuegt werden kann
		ChartPanel chP = new ChartPanel(barChart);
		chP.setPreferredSize(new Dimension(1260, 300));
		chP.setPopupMenu(null);
		chP.setDomainZoomable(false);
		chP.setRangeZoomable(false);
		graphPanel.add(chP, BorderLayout.CENTER);

	}

	/**
	 * Getter fuer das GraphPanel
	 * 
	 * @return das graphPanel
	 */
	public JPanel getGraphPanel() {
		return graphPanel;
	}

	/**
	 * Setter fuer das GraphPanel
	 * 
	 * @param graphPanel das neue graphPanel
	 */
	public void setGraphPanel(JPanel graphPanel) {
		this.graphPanel = graphPanel;
	}
}
