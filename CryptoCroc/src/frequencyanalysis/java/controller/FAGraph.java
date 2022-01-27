package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
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
	private double[] german;
	private double[] actual;
	private String percentage = "Prozentzahl";
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Konstruktor fuer einen FAGraph, erstellt das GraphPanel
	 * 
	 * @param fGerman deutsche Buchstabenverteilung
	 * @param fActual eigentliche Buchstabenverteilung
	 * @throws IOException
	 */
	public FAGraph(float[] fGerman, float[] fActual) throws IOException {
		this.german = new double[fGerman.length];
		for (int i = 0; i < fGerman.length; i++) {
			this.german[i] = Double.parseDouble(Float.toString(fGerman[i]));
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
	 * @throws IOException
	 */
	public void createGraphPanel() throws IOException {
		// zum Erstellen eines DataSets
		DefaultKeyedValues2DDataset m = new DefaultKeyedValues2DDataset();

		for (int i = 0; i < german.length; i++) {
			// Wert alle nacheinander
			// Reihenschluessel, gleiche gehoeren zur gleichen Series
			// Zeilenschluessel, gleiche gehoeren zum gleichen Buchstaben des Alphabets
			m.addValue(german[i], "Deutsche Verteilung", Character.toString(alphabet.charAt(i)));
			m.addValue(actual[i], "Verteilung im Text", Character.toString(alphabet.charAt(i)));
		}

		// Bar chart eingabe parameter (Titel oben, Titel unten, Y Achse, dataset,
		// ausrichtung der Balken, Legende erzeugen, Tooltips, URLs)
		JFreeChart barChart = ChartFactory.createBarChart("H\u00e4ufigkeitsverteilung", "", percentage, m,
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
		barChart.addLegend(legend);

		// Hintergrund des plots (standard ist grau) auf weiss setzen
		cplot.setBackgroundPaint(Color.white);

		// Farben der Bars entsprechend ihrem Reihenschluessel faerben
		((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer render = (BarRenderer) barChart.getCategoryPlot().getRenderer();
		// CryptoCroc gruen
		render.setSeriesPaint(0, new Color(74, 115, 14));
		// gelb/orange/gold
		render.setSeriesPaint(1, new Color(242, 194, 9));
		ChartUtils.saveChartAsPNG(new File("ex.png"), barChart, 2000, 800);

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
