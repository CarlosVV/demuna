var txtFechaIni = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Del',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaIni'
});

var txtFechaFin = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Al',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaFin'
});

var btnMostrarFechas = new Ext.Button({
	id : 'btnMostrarFechas',
	width : 50,
	heigth : 40,
	icon : './icon/ok111.png',
	handler : function() {
		strData.load({
			params : {
				ini : txtFechaIni.value,
				fin : txtFechaFin.value
			}
		});
	}
});
// url:'agrafdemuna.php?cargar=true&demuna='+txtKyDemunaIni.getValue(),
var strData = new Ext.data.JsonStore({
	url : 'reportes/agrafdemuna.htm',
	root : 'data',
	total : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'Nombres', 'Casos' ]
});

strData.load();

Ext.onReady(function() {
	var store = new Ext.data.ArrayStore({
		fields : [ 'Nombres', 'Casos' ],
		data : strData
	});

	new Ext.Panel({
		width : 1000,
		height : 500,
		renderTo : 'DivformExpediente',
		title : 'Graficos por Materias de Esta Demuna',
		tbar : [ txtFechaIni, txtFechaFin, btnMostrarFechas, {
			text : 'Mostrar Todo',
			handler : function() {
				strData.load();
				store.loadData(strData);
			}
		} ],
		items : {
			xtype : 'columnchart',
			store : strData,
			yField : 'Casos',
			url : './resources/charts.swf',
			xField : 'Nombres',
			xAxis : new Ext.chart.CategoryAxis({
				title : 'Materias'
			}),
			yAxis : new Ext.chart.NumericAxis({
				title : '# Casos '
			}),
			extraStyle : {
				xAxis : {
					labelRotation : -45
				}
			}
		}
	});
});