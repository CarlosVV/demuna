var winNuevaCompromiso;

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
		winNuevaCompromiso.hide();
	}
});

var btnCompromiso = new Ext.Button({
	id : 'btnCompromiso',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Nueva Acta de Compromiso',
	icon : './icon/exec.png',
	handler : function() {
		AbrirSolictudes();
		limpiarControles();
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

		var storeNuevaCompromiso = new Ext.data.JsonStore({
			url : 'formcompromiso/nuevacompromiso.htm'// url:'formcompromiso.php?nuevacompromiso=true'
		});
		var Ky = txtKy.getValue();
		var KyExpediente = txtKyExpediente.getValue();
		var DescripcionCaso = TxtDescripcionCaso.getValue();
		var Compromiso = TxtCompromiso.getValue();
		var Seguimiento = TxtSeguimiento.getValue();
		var FechaCompromiso = txtFechaCompromiso.value;

		storeNuevaCompromiso.load({
			params : {
				Ky : Ky,
				KyExpediente : KyExpediente,
				Compromiso : Compromiso,
				Seguimiento : Seguimiento,
				DescripcionCaso : DescripcionCaso,
				FechaCompromiso : FechaCompromiso
			},
			callback : function() {
				estoreActas.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
		winNuevaCompromiso.hide();
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

var txtFechaCompromiso = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaCompromiso'

});

var txtLugar = new Ext.form.TextField({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	readOnly : true,
	width : 450
});

var TxtCompromiso = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtCompromiso',
	readOnly : false,
	width : 770,
	height : 200
});

var TxtDescripcionCaso = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtDescripcionCaso',
	readOnly : false,
	width : 770,
	height : 100
});
var TxtSeguimiento = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtSeguimiento',
	readOnly : false,
	width : 770,
	height : 100
});

var FieldGrupoExpediente01 = new Ext.form.FieldSet({
	title : 'Expediente',
	collapsible : true,
	collapsed : true,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKyExpediente, TxtNroExpediente, txtFecha, txtLugar ]
});

var FieldGrupoCompromiso02 = new Ext.form.FieldSet({
	title : 'Caso, Compromisos y Seguimiento',
	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKy, TxtDescripcionCaso, TxtCompromiso, TxtSeguimiento,
			txtFechaCompromiso ]
});

function AbrirSolictudes() {
	if (!winNuevaCompromiso) {
		winNuevaCompromiso = new Ext.Window(
				{
					layout : 'form',
					title : 'Registro de Acta de Compromiso',
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
					items : [ FieldGrupoExpediente01, FieldGrupoCompromiso02 ],
					tbar : [ '->', btnCerrar ],
					bbar : [ '->', btGuardar ],
					listeners : {
						show : function() {

							var estoreActa = new Ext.data.JsonStore(
									{
										url : 'formcompromiso/cargardatoscompromiso.htm',// 'formcompromiso.php?cargardatoscompromiso=true',
										root : 'data',
										totalProperty : 'total',
										paramNames : {
											start : 'offset',
											limit : 'size',
											sort : 'sort',
											dir : 'dir'
										},
										fields : [ 'KyExpediente',
												'NroExpediente', 'Compromiso',
												'DescripcionCaso',
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
	winNuevaCompromiso.show();
}
function limpiarControles() {
	txtKy.setValue('00');
	txtFechaCompromiso.setValue('');
	TxtDescripcionCaso.setValue('');
	TxtCompromiso.setValue('');
	TxtSeguimiento.setValue('');

}
