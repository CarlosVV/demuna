var winNuevaConciliacion;

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// ///////////////////////// ///////////////////////////
// //////////////////////// PARA DATOS DE LA EXPEDIENTE
// ///////////////////////////
// //////////////////////// ///////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////

var btnCerrar = new Ext.Button({
	id : 'btnCerrar',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Cerrar',
	icon : './icon/cerrar.png',
	handler : function() {
		winNuevaConciliacion.hide();
	}
});

var btnConciliacion = new Ext.Button({
	id : 'btnConciliacion',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Nueva Acta de Conciliacion',
	icon : './icon/exec.png',
	handler : function() {

		// ////////////////////////////////////////
		// //////////////////////////////////////// REPASAR DATOS DE
		// CONCILIACION
		var procesarSI;
		procesarSI = 0;
		grid1Mater.store.each(function(record, index) {
			var sino = record.get('Conciliable');
			if (sino == "SI") {
				procesarSI = 1;
			}
		});
		if (procesarSI == 1) {
			AbrirSolictudes();
			limpiarControles();
		} else {
			Ext.Msg.alert("Advertencia",
					"Imposible Crear Acta de Conciliacion.");
		}

		// ////////////////////////////////////////
		// ////////////////////////////////////////

	}
});

var btGuardar = new Ext.Button({
	id : 'btGuardar',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Guardar',
	icon : './icon/disk.png',
	handler : function() {

		var storeNuevaConciliacion = new Ext.data.JsonStore({
			url : 'formconciliaciones/nuevaconciliacion.htm'// 'formconciliaciones.php?nuevaconciliacion=true'
		});
		var Ky = txtKy.getValue();
		var KyExpediente = txtKyExpediente.getValue();
		var ck1 = chk01.getValue();
		var ck2 = chk02.getValue();
		var ck3 = chk03.getValue();
		var ck4 = chk04.getValue();
		var ck5 = chk05.getValue();
		var rad = 0;
		if (rdo01.getValue() == true) {
			rad = 1;
		}
		if (rdo02.getValue() == true) {
			rad = 2;
		}
		if (rdo03.getValue() == true) {
			rad = 3;
		}
		if (rdo04.getValue() == true) {
			rad = 4;
		}
		if (rdo05.getValue() == true) {
			rad = 5;
		}
		if (rdo06.getValue() == true) {
			rad = 6;
		}

		var Informe = TxtInforme.getValue();
		var Otros = TxtOtros.getValue();
		var FechaConciliacion = txtFechaConciliacion.value;

		storeNuevaConciliacion.load({
			params : {
				Ky : Ky,
				KyExpediente : KyExpediente,
				Informe : Informe,
				ck1 : ck1,
				ck2 : ck2,
				ck3 : ck3,
				ck4 : ck4,
				ck5 : ck5,
				rad : rad,
				Otros : Otros,
				FechaConciliacion : FechaConciliacion
			},
			callback : function() {
				estoreActas.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
		winNuevaConciliacion.hide();
	}
});

var txtKy = new Ext.form.TextField({
	fieldLabel : 'Codigo',
	name : 'txtKy',
	readOnly : true,
	style : 'text-align:center;color:#FF6600;',
	width : 30,
	value : '00'
});

var txtKyExpediente = new Ext.form.TextField({
	fieldLabel : 'Codigo',
	name : 'txtKyExpediente',
	readOnly : true,
	style : 'text-align:center;color:#FF6600;',
	width : 40,
	value : '00'
});

var TxtNroExpediente = new Ext.form.TextField({
	fieldLabel : 'Expediente',
	name : 'TxtNroExpediente',
	readOnly : true,
	style : 'text-align:center;color:#FF6600;',
	width : 200
});

var txtFecha = new Ext.form.TextField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	readOnly : true,
	width : 200,
	name : 'txtFecha'

});

var txtFechaConciliacion = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaConciliacion'

});

var txtLugar = new Ext.form.TextField({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	readOnly : true,
	width : 450
});

var TxtInforme = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtInforme',
	readOnly : false,
	width : 770,
	height : 90
});

var TxtOtros = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtOtros',
	readOnly : false,
	width : 770,
	height : 90
});

var chk01 = new Ext.form.Checkbox({
	fieldLabel : 'Alimentos',
	name : 'chk01'
});

var chk02 = new Ext.form.Checkbox({
	fieldLabel : 'Regimen Visitas',
	name : 'chk02'
});

var chk03 = new Ext.form.Checkbox({
	fieldLabel : 'Tenencia',
	name : 'chk03'
});
var chk04 = new Ext.form.Checkbox({
	fieldLabel : 'Pensiones',
	name : 'chk04'
});

var chk05 = new Ext.form.Checkbox({
	fieldLabel : 'Otros',
	name : 'chk05'
});

var fieldIzquierda = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 160,
	items : [ chk01 ]
});

var fieldIzquierda2 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 160,
	items : [ chk02 ]
});

var fieldIzquierda3 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 160,
	items : [ chk03 ]
});

var fieldIzquierda4 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 160,
	items : [ chk04 ]
});

var fieldIzquierda5 = new Ext.form.FieldSet({
	style : 'float:left;',
	width : 160,
	items : [ chk05 ]
});

var rdo01 = new Ext.form.Radio(
		{
			checked : true,
			fieldLabel : 'Acuerdos',
			boxLabel : 'Conciliar todos los puntos en controversia, en los siguientes términos y condiciones',
			name : 'favacuerdo',
			inputValue : '1'
		});

var rdo02 = new Ext.form.Radio(
		{
			fieldLabel : '',
			labelSeparator : '',
			boxLabel : 'Conciliar parte de los puntos en controversia, en los siguientes términos y condiciones',
			name : 'favacuerdo',
			inputValue : '2'
		});

var rdo03 = new Ext.form.Radio(
		{
			fieldLabel : '',
			labelSeparator : '',
			boxLabel : 'Dejando constancia de la falta de acuerdo en los siguientes puntos en controversia',
			name : 'favacuerdo',
			inputValue : '3'
		});

var rdo04 = new Ext.form.Radio({
	fieldLabel : '',
	labelSeparator : '',
	boxLabel : 'Desistir de la conciliación',
	name : 'favacuerdo',
	inputValue : '4'
});

var rdo05 = new Ext.form.Radio({
	fieldLabel : '',
	labelSeparator : '',
	boxLabel : 'Inasistencia ambas partes a una audiencia de conciliación',
	name : 'favacuerdo',
	inputValue : '5'
});

var rdo06 = new Ext.form.Radio(
		{
			fieldLabel : '',
			labelSeparator : '',
			boxLabel : 'Inasistencia una de las  partes a dos audiencias de conciliación',
			name : 'favacuerdo',
			inputValue : '6'
		});

var fieldDerecha = new Ext.form.FieldSet({
	// collapsible: true,
	autoHeight : true,
	defaultType : 'radio',
	items : [ rdo01, rdo02, rdo03, rdo04, rdo05, rdo06 ]
});

var FieldGrupoExpediente01 = new Ext.form.FieldSet({
	title : 'Expediente',
	collapsible : true,
	collapsed : true,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKyExpediente, TxtNroExpediente, txtFecha, txtLugar ]
});

var FieldGrupoConciliacion02 = new Ext.form.FieldSet({
	title : 'Acuerdos y Seguimiento',
	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKy, TxtInforme, TxtOtros, txtFechaConciliacion ]
});

function AbrirSolictudes() {
	if (!winNuevaConciliacion) {
		winNuevaConciliacion = new Ext.Window({
			layout : 'form',
			title : 'Registro de Acta de Conciliacion',
			bodyStyle : 'padding: 10px; background-color:#FFFFFF;',
			width : 850,
			height : 650,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			draggable : true,
			plain : false,
			border : false,
			modal : true,
			style : 'margin:10px;',
			items : [ FieldGrupoExpediente01, fieldIzquierda, fieldIzquierda2,
					fieldIzquierda3, fieldIzquierda4, fieldIzquierda5,
					fieldDerecha, FieldGrupoConciliacion02 ],
			tbar : [ '->', btnCerrar ],
			bbar : [ '->', btGuardar ],
			listeners : {
				show : function() {

					var estoreActa = new Ext.data.JsonStore({
						url : 'formconciliaciones/cargardatosconciliacion.htm',// 'formconciliaciones.php?cargardatosconciliacion=true',
						root : 'data',
						totalProperty : 'total',
						paramNames : {
							start : 'offset',
							limit : 'size',
							sort : 'sort',
							dir : 'dir'
						},
						fields : [ 'KyExpediente', 'NroExpediente', 'Acuerdos',
								'Seguimiento' ]
					});
					estoreActa.load({
						params : {
							expediente : txtKyExpediente.getValue()
						}
					});

				}

			}
		});
	}
	winNuevaConciliacion.show();
}

function limpiarControles() {
	txtKy.setValue('00');
	txtFechaConciliacion.setValue('');
	TxtInforme.setValue('');
	TxtOtros.setValue('');

	chk01.setValue(false);
	chk02.setValue(false);
	chk03.setValue(false);
	chk04.setValue(false);
	chk05.setValue(false);

	rdo01.setValue(false);
	rdo02.setValue(false);
	rdo03.setValue(false);
	rdo04.setValue(false);
	rdo05.setValue(false);
	rdo06.setValue(false);

}