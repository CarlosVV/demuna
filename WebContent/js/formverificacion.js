var winNuevaVerificacion;

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
		winNuevaVerificacion.hide();
	}
});

var btnVerificacion = new Ext.Button({
	id : 'btnVerificacion',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Nueva Acta de Verificacion',
	icon : './icon/exec.png',
	handler : function() {
		AbrirSolictudes();
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

		var storeNuevaVerificacion = new Ext.data.JsonStore({
			url : 'formverificacion/nuevaverificacion.htm'
		});
		var Ky = txtKy.getValue();
		var KyExpediente = txtKyExpediente.getValue();
		var HoraInicio = TxtHoraInicio.getValue();
		var HoraFin = TxtHoraFin.getValue();
		var MotVerificacion = TxtMotVerificacion.getValue();

		var Autorizacion = TxtAutorizacion.getValue();
		var DocIdentidad = TxtDocIdentidad.getValue();
		var Nombres = TxtDefNombres.getValue();
		var Appaterno = TxtDefAppaterno.getValue();
		var Apmaterno = TxtDefApmaterno.getValue();

		var Observacion = TxtObservacion.getValue();
		var Concluciones = TxtConcluciones.getValue();
		var FechaVerificacion = txtFechaVerificacion.value;

		storeNuevaVerificacion.load({
			params : {
				Ky : Ky,
				KyExpediente : KyExpediente,
				HoraInicio : HoraInicio,
				HoraFin : HoraFin,
				MotVerificacion : MotVerificacion,
				Autorizacion : Autorizacion,
				DocIdentidad : DocIdentidad,
				Nombres : Nombres,
				Appaterno : Appaterno,
				Apmaterno : Apmaterno,
				Observacion : Observacion,
				Concluciones : Concluciones,
				FechaVerificacion : FechaVerificacion
			},
			callback : function() {
				estoreActas.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
		winNuevaVerificacion.hide();
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

var txtFechaVerificacion = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaVerificacion'

});

var txtLugar = new Ext.form.TextField({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	readOnly : true,
	width : 450
});

var TxtHoraInicio = new Ext.form.TextField({
	fieldLabel : 'Hora Inicio',
	name : 'TxtHoraInicio',
	readOnly : false,
	style : 'color:#006600;',
	width : 100
});

var TxtHoraFin = new Ext.form.TextField({
	fieldLabel : 'Hora Fin',
	name : 'TxtHoraFin',
	readOnly : false,
	style : 'color:#006600;',
	width : 100
});

var TxtMotVerificacion = new Ext.form.TextField({
	fieldLabel : 'Motivo',
	name : 'TxtMotVerificacion',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});
// ////////////////////
var TxtAutorizacion = new Ext.form.TextField({
	fieldLabel : 'Autorización',
	name : 'TxtAutorizacion',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtDocIdentidad = new Ext.form.TextField({
	fieldLabel : 'DNI',
	name : 'TxtDocIdentidad',
	readOnly : false,
	style : 'color:#006600;',
	width : 120
});

var TxtDefNombres = new Ext.form.TextField({
	fieldLabel : 'Nombres Defensor',
	name : 'TxtDefNombres',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtDefAppaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Pat. Defensor',
	name : 'TxtDefAppaterno',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtDefApmaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Mat. Defensor',
	name : 'TxtDefApmaterno',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});
// ////////////////////////////

var TxtObservacion = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtObservacion',
	readOnly : false,
	width : 770,
	height : 75
});

var TxtConcluciones = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtConcluciones',
	readOnly : false,
	width : 770,
	height : 75
});

var FieldGrupoExpediente01 = new Ext.form.FieldSet({
	title : 'Expediente',
	collapsible : true,
	collapsed : true,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKyExpediente, TxtNroExpediente, txtFecha, txtLugar ]
});

var FieldGrupoVerificacion02 = new Ext.form.FieldSet({
	title : 'Caso, Verificacion y Seguimiento',
	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKy, TxtHoraInicio, TxtHoraFin, TxtMotVerificacion,
			TxtAutorizacion, TxtDocIdentidad, TxtDefNombres,TxtDefAppaterno,TxtDefApmaterno, TxtObservacion,
			TxtConcluciones, txtFechaVerificacion ]
});

function AbrirSolictudes() {
	if (!winNuevaVerificacion) {
		winNuevaVerificacion = new Ext.Window({
			layout : 'form',
			title : 'Registro de Acta de Verificacion',
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
			items : [ FieldGrupoExpediente01, FieldGrupoVerificacion02 ],
			tbar : [ '->', btnCerrar ],
			bbar : [ '->', btGuardar ],
			listeners : {
				show : function() {

					var estoreActa = new Ext.data.JsonStore({
						url : 'formverificacion/cargardatosverificacion.htm',
						root : 'data',
						totalProperty : 'total',
						paramNames : {
							start : 'offset',
							limit : 'size',
							sort : 'sort',
							dir : 'dir'
						},
						fields : [ 'KyExpediente', 'NroExpediente',
								'HoraInicio', 'HoraFin', 'MotVerificacion',
								'Autorizacion', 'DocIdentidad', 'Defensor','Nombres','Appaterno','Apmaterno',
								'Observacion', 'Concluciones' ]
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
	winNuevaVerificacion.show();
}
function limpiarControles() {
	txtKy.setValue('00');
	TxtHoraInicio.setValue('');
	TxtHoraFin.setValue('');
	TxtMotVerificacion.setValue('');
	TxtAutorizacion.setValue('');
	TxtDocIdentidad.setValue('');
	TxtDefNombres.setValue('');
	TxtDefAppaterno.setValue('');
	TxtDefApmaterno.setValue('');
	TxtObservacion.setValue('');
	TxtConcluciones.setValue('');
	FechaVerificacion.setValue('');
}
