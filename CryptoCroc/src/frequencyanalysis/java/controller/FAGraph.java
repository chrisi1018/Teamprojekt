package controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
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
		DefaultKeyedValues2DDataset m = new DefaultKeyedValues2DDataset();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < german.length; i++) {
			m.addValue(german[i], "0", Character.toString(alphabet.charAt(i)));
		}
		for (int i = 0; i < actual.length; i++) {
			m.addValue(actual[i], "1", Character.toString(alphabet.charAt(i)));
		}
		JFreeChart histogram = ChartFactory.createBarChart("Haufigkeitsverteilung", "", percentage, m,
				PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot cplot = (CategoryPlot) histogram.getPlot();
		cplot.setBackgroundPaint(Color.white);
		((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer render = (BarRenderer) histogram.getCategoryPlot().getRenderer();
		render.setSeriesPaint(0, new Color(74, 115, 14));
		render.setSeriesPaint(1, new Color(242, 194, 9));
		ChartUtils.saveChartAsPNG(new File("ex.png"), histogram, 2000, 800);
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
