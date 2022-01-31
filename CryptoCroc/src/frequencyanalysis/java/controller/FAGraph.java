package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
 * @version 1.0
 */
public class FAGraph {

	private JPanel graphPanel;
	// Buchstabenverteilungen
	private double[] language;
	private double[] actual;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Konstruktor fuer einen FAGraph, erstellt das GraphPanel
	 * 
	 * @param fLanguage Buchstabenverteilung einer uebergebenen Sprache
	 * @param fActual   eigentliche Buchstabenverteilung im Text
	 */
	public FAGraph(float[] fLanguage, float[] fActual) throws IOException {
		this.language = new double[fLanguage.length];
		for (int i = 0; i < fLanguage.length; i++) {
			this.language[i] = Double.parseDouble(Float.toString(fLanguage[i]));
		}
		this.actual = new double[fActual.length];
		for (int i = 0; i < fActual.length; i++) {
			this.actual[i] = Double.parseDouble(Float.toString(fActual[i]));
		}
		createGraphPanel();
	}

	/**
	 * Erstellt das GraphPanel
	 * 
	 */
	public void createGraphPanel() {
		// zum Erstellen eines DataSets
		DefaultKeyedValues2DDataset m = new DefaultKeyedValues2DDataset();

		for (int i = 0; i < language.length; i++) {
			// Wert alle nacheinander
			// Reihenschluessel, gleiche gehoeren zur gleichen Series
			// Zeilenschluessel, gleiche gehoeren zum gleichen Buchstaben des Alphabets
			// TODO andere Sprachen auch ermoeglichen
			m.addValue(language[i], "Deutsche Verteilung", Character.toString(alphabet.charAt(i)));
			m.addValue(actual[i], "Verteilung im Text", Character.toString(alphabet.charAt(i)));
		}

		// Bar chart eingabe parameter (Titel oben, Titel unten, Y Achse, dataset,
		// ausrichtung der Balken, Legende erzeugen, Tooltips, URLs)
		JFreeChart barChart = ChartFactory.createBarChart("H\u00e4ufigkeitsverteilung", "", "", m,
				PlotOrientation.VERTICAL, false, false, false);

		// Plot holen des Charts und manuell Legende hinzufuegen; nur so koennen
		// Orientierung und Textstil angepasst werden
		CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
		LegendTitle legend = new LegendTitle(cplot);
		Font font = new Font(Font.DIALOG, Font.BOLD, 20);
		legend.setItemFont(font);
		// Legende soll unten links
		legend.setPosition(RectangleEdge.BOTTOM);
		legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		// padding zwischen einzelnen Legendenitems
		legend.setItemLabelPadding(new RectangleInsets(2, 2, 2, 30));
		barChart.addLegend(legend);

		// Hintergrund des plots (standard ist grau) auf weiss setzen und y-Achse mit
		// Zahlenwerten ausblenden
		cplot.setBackgroundPaint(Color.white);
		cplot.getRangeAxis().setVisible(false);

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
		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesPositiveItemLabelPosition(0,
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		renderer.setItemLabelAnchorOffset(10);
		renderer.setSeriesItemLabelGenerator(1, generator);
		renderer.setSeriesItemLabelsVisible(1, true);
		renderer.setSeriesPositiveItemLabelPosition(1,
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		renderer.setItemLabelAnchorOffset(10);

		graphPanel = new JPanel();

		// konvertieren zu ChartPanel sodass es zum graphPanel hinzugefuegt werden kann
		ChartPanel chP = new ChartPanel(barChart);
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
