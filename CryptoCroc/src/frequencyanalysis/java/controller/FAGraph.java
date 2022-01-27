package controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

import model.FAData;

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
		DefaultCategoryDataset hg = new DefaultCategoryDataset();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for (int i = 0; i < german.length; i++) {
			hg.setValue(german[i], percentage, Character.toString(alphabet.charAt(i)));
		}
		
		JFreeChart histogram = ChartFactory.createBarChart("Haufigkeitsverteilung", "", percentage, hg,
				PlotOrientation.VERTICAL, false, true, false);
		
		ChartUtils.saveChartAsPNG(new File("ex.png"), histogram, 1000, 800);
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
