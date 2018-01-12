var winNuevaEntrevista;

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
		winNuevaEntrevista.hide();
	}
});

var btnEntrevista = new Ext.Button({
	id : 'btnEntrevista',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Generar Nueva Entrevista',
	icon : './icon/exec.png',
	handler : function() {
		AbrirEntrevistas();
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
		var storeNuevaEntrevista = new Ext.data.JsonStore({
			url : 'formentrevistas/nuevaEntrevista.htm'// url:'formentrevistas.php?nuevaEntrevista=true'
		});

		var Ky = txtKy.getValue();
		var KyExpediente = txtKyExpediente.getValue();
		var Nombres = txtEntNombres.getValue();
		var Appaterno = txtEntAppaterno.getValue();
		var Apmaterno = txtEntApmaterno.getValue();
		var Tipo = TxtTipo.getValue();
		var FechaEntrevista = txtFechaEntrevista.value;
		var Informe = TxtInforme.getValue();
		var Otros = TxtOtros.getValue();

		storeNuevaEntrevista.load({
			params : {
				Ky : Ky,
				KyExpediente : KyExpediente,
				Nombres : Nombres,
				Appaterno : Appaterno,
				Apmaterno : Apmaterno,
				Tipo : Tipo,
				FechaEntrevista : FechaEntrevista,
				Informe : Informe,
				Otros : Otros
			},
			callback : function() {
				estoreEntrevista.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
		winNuevaEntrevista.hide();
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

var txtFechaEntrevista = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaEntrevista'

});

var txtLugar = new Ext.form.TextField({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	readOnly : true,
	width : 450
});

var TxtTipo = new Ext.form.TextField({
	fieldLabel : 'Entrevistado',
	name : 'TxtTipo',
	readOnly : true,
	width : 200
});

var txtEntNombres = new Ext.form.TextField({
	fieldLabel : 'Nombres',
	name : 'txtEntNombres',
	readOnly : true,
	width : 350
});

var txtEntAppaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Paterno',
	name : 'txtEntAppaterno',
	readOnly : true,
	width : 350
});

var txtEntApmaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Materno',
	name : 'txtEntApmaterno',
	readOnly : true,
	width : 350
});

var txtEntrevistadoDireccion = new Ext.form.Hidden({
	fieldLabel : 'Direccion',
	name : 'txtEntrevistadoDireccion',
	readOnly : true,
	width : 350
});

var txtEntrevistadoDni = new Ext.form.Hidden({
	fieldLabel : 'Dni',
	name : 'txtEntrevistadoDni',
	style : 'float:left;',
	readOnly : true,
	width : 100
});

var txtEntrevistadoEdad = new Ext.form.Hidden({
	fieldLabel : 'Edad',
	name : 'txtEntrevistadoEdad',
	style : 'float:left;',
	readOnly : true,
	width : 50
});

var txtEntrevistadoSexo = new Ext.form.Hidden({
	fieldLabel : 'Sexo',
	name : 'txtEntrevistadoSexo',
	style : 'float:left;',
	readOnly : true,
	width : 50
});

var TxtInforme = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtInforme',
	readOnly : false,
	width : 770,
	height : 200
});

var TxtOtros = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtOtros',
	readOnly : false,
	width : 770,
	height : 75
});

var btnBuscarEntrevistado = new Ext.Button({
	id : 'btnBuscarEntrevistado',
	scale : 'large',
	iconAlign : 'top',
	text : '',
	icon : './icon/buscar.png',
	handler : function() {
		AbrirBuscarActor();
	}
});

var FieldGrupoExpediente01 = new Ext.form.FieldSet({
	title : 'Expediente',
	collapsible : true,
	collapsed : true,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKyExpediente, TxtNroExpediente, txtFecha, txtLugar ]
});

var fieldIzquierda = new Ext.form.FieldSet({
	style : 'float:left; margin-bottom:5px;',
	width : 820,
	// collapsible: true,
	autoHeight : true,
	items : [ btnBuscarEntrevistado, txtKy, TxtTipo, txtEntNombres,
			txtEntAppaterno, txtEntApmaterno, txtEntrevistadoDireccion ]
});

var fieldDerecha = new Ext.form.FieldSet({
	style : 'float:left; margin:5px;',
	width : 270,
	// collapsible: true,
	autoHeight : true,
	items : [ txtEntrevistadoDni, txtEntrevistadoEdad, txtEntrevistadoSexo ]
});

var FieldGrupoEntrevista02 = new Ext.form.FieldSet({

	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ TxtInforme, TxtOtros, txtFechaEntrevista ]
});

function AbrirEntrevistas() {
	if (!winNuevaEntrevista) {
		winNuevaEntrevista = new Ext.Window({
			layout : 'form',
			title : 'Nuevo/Editar Ficha de Entrevista',
			bodyStyle : 'padding: 10px; background-color:#FFFFFF;',
			width : 850,
			height : 670,
			resizable : false,
			closeAction : 'hide',
			closable : true,
			draggable : true,
			plain : false,
			border : false,
			modal : true,
			style : 'margin:10px;',
			items : [ FieldGrupoExpediente01, fieldIzquierda,
					FieldGrupoEntrevista02 ],
			tbar : [ '->', btnCerrar ],
			bbar : [ '->', btGuardar ],
			listeners : {
				show : function() {

				}
			}
		});
	}
	winNuevaEntrevista.show();
}

function limpiarControles() {
	txtFechaEntrevista.setValue('');
	TxtTipo.setValue('');
	txtEntNombres.setValue('');
	txtEntAppaterno.setValue('');
	txtEntApmaterno.setValue('');
	TxtInforme.setValue('');
	TxtOtros.setValue('');
	txtKy.setValue('00');
}
