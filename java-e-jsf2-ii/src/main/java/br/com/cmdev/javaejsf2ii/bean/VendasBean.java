package br.com.cmdev.javaejsf2ii.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;

import br.com.cmdev.javaejsf2ii.business.LivroBusiness;
import br.com.cmdev.javaejsf2ii.model.Livro;
import br.com.cmdev.javaejsf2ii.model.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {
	
	private BarChartModel barModel;

	public List<Venda> getVendas() throws Exception {

		List<Livro> livros = new LivroBusiness().listar();

		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(1234);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

	/*
	public BarChartModel getVendasModel() throws Exception {
		createPieModel();
		BarChartModel model = new BarChartModel();
		BarChartDataSet barDataSet = new BarChartDataSet();
		ChartData data = new ChartData();
		barDataSet.setLabel("Vendas do mês");
		
		List<Venda> vendas = getVendas();
		
		List<Number> values = new ArrayList<>();
		for (Venda venda : vendas) {
			values.add(venda.getQuantidade());
		}
		barDataSet.setData(values);

		data.addChartDataSet(barDataSet);

		model.setData(data);
		

		return model;
	}
	*/
	
    public BarChartModel getVendasModel() throws Exception {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Vendas de Livros");

        List<Venda> vendas = getVendas();
        List<Number> values = new ArrayList<>();
        for (Venda venda : vendas) {
        	values.add(venda.getQuantidade());
		}
        barDataSet.setData(values);
        
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (Venda venda : vendas) {
        	labels.add(venda.getLivro().getTitulo());
		}
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        /*
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);
        */

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(600);
        options.setAnimation(animation);

        barModel.setOptions(options);
        
        return barModel;
    }

}
