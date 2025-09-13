package manager;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

import matjipDAO.CircleChartDAO;

public class CircleChart {

	public void showChart(String matjipId) throws SQLException, ClassNotFoundException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		CircleChartDAO matdao = new CircleChartDAO();
		List<String[]> chartData = matdao.circleChart(matjipId);

		for (String[] row : chartData) {
			String menuName = row[0];
			double totalSales = Double.parseDouble(row[1]);
			dataset.setValue(menuName, totalSales);
		}

		JFreeChart jfchart = ChartFactory.createPieChart3D("메뉴별 매출 차트", dataset, true, true, false);
		jfchart.setBackgroundPaint(Color.YELLOW);

		jfchart.getTitle().setFont(new Font("맑은 고딕", Font.BOLD, 18));

		PiePlot3D plot = (PiePlot3D) jfchart.getPlot();
		plot.setLabelFont(new Font("맑은 고딕", Font.PLAIN, 14));

		LegendTitle legend = jfchart.getLegend();
		legend.setItemFont(new Font("맑은 고딕", Font.PLAIN, 12));

		ChartFrame cf2 = new ChartFrame("원 그래프", jfchart);
		cf2.pack();
		cf2.setSize(1000, 500);
		cf2.setVisible(true);
	}
}
