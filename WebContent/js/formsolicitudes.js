var winNuevaSolicitud;

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
		winNuevaSolicitud.hide();
	}
});

var btnSolicitud = new Ext.Button({
	id : 'btnSolicitud',
	width : 80,
	heigth : 30,
	iconAlign : 'top',
	text : 'Generar Nueva Derivacion',
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

		var storeNuevaSolicitud = new Ext.data.JsonStore({
			url : 'formsolicitudes/nuevasolicitud.htm'
		});
		var Ky = txtKy.getValue();
		var KyExpediente = txtKyExpediente.getValue();
		var KyAfectado = comboAfectadoApoyo.getValue();
		var KyTipoAtencion = comboTipoApoyo.getValue();
		var Nombres = TxtSolNombres.getValue();
		var Appaterno = TxtSolAppaterno.getValue();
		var Apmaterno = TxtSolApmaterno.getValue();
		var FechaSolicitud = txtFechaSolicitud.value;
		var Cargo = TxtCargo.getValue();
		var Institucion = TxtInstitucion.getValue();
		var Informe = TxtInforme.getValue();
		var Otros = TxtOtros.getValue();
		var Derivar = CboDeriva.getValue();

		storeNuevaSolicitud.load({
			params : {
				Ky : Ky,
				KyExpediente : KyExpediente,
				KyAfectado : KyAfectado,
				KyTipoAtencion : KyTipoAtencion,
				Nombres : Nombres,
				Appaterno : Appaterno,
				Apmaterno : Apmaterno,
				FechaSolicitud : FechaSolicitud,
				Cargo : Cargo,
				Institucion : Institucion,
				Informe : Informe,
				Otros : Otros,
				Derivar : Derivar
			},
			callback : function() {
				estoreSolicitud.load({
					params : {
						expediente : txtKyExpediente.getValue()
					}
				});
			}
		});
		winNuevaSolicitud.hide();
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

var txtFechaSolicitud = new Ext.form.DateField({
	style : 'margin:1px;',
	fieldLabel : 'Fecha',
	format : 'd-m-Y',
	width : 120,
	name : 'txtFechaSolicitud'

});

var txtLugar = new Ext.form.Hidden({
	fieldLabel : 'Lugar',
	name : 'txtLugar',
	readOnly : true,
	width : 450
});

var storeTipoApoyo = new Ext.data.JsonStore({
	url : 'formsolicitudes/cargartipoapoyo.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyTipoAtencion', 'Atencion' ]
});
storeTipoApoyo.load();

var comboTipoApoyo = new Ext.form.ComboBox({
	fieldLabel : 'Tipo Apoyo',
	name : 'comboTipoApoyo',
	forceSelection : true,
	store : storeTipoApoyo,
	emptyText : 'Tipo Apoyo',
	triggerAction : 'all',
	editable : false,
	displayField : 'Atencion',
	mode : 'local',
	valueField : 'KyTipoAtencion',
	allowBlank : false,
	blankText : 'Tipo Apoyo',
	width : 350
});

var dataderivacion = [ 'a.	Policia Nacional',
		'b. Ministerio Publico (Fiscalia de familia)',
		'c. Ministerio Publico (Fiscalia penal)',
		'd. Ministerio Publico (Fiscalia Mixta)', 'e. Juzgado',
		'f. Centro de Emergencia Mujer', 'g. Centro de Salud' ];
var CboDeriva = new Ext.form.ComboBox({
	fieldLabel : 'Deriva a',
	name : 'CboDeriva',
	forceSelection : true,
	allowBlank : false,
	blankText : 'Seleecione el Estado',
	store : dataderivacion,
	emptyText : 'Seleccione Estado',
	triggerAction : 'all',
	editable : false,
	value : 'a. Policía Nacional',
	width : 250
});

var storeAfectadoApoyo = new Ext.data.JsonStore({
	url : 'formsolicitudes/cargarafectado.htm',
	root : 'data',
	totalProperty : 'total',
	paramNames : {
		start : 'offset',
		limit : 'size',
		sort : 'sort',
		dir : 'dir'
	},
	fields : [ 'KyAfectado', 'Nombre' ]
});

var comboAfectadoApoyo = new Ext.form.ComboBox({
	fieldLabel : 'Atender a',
	name : 'comboAfectadoApoyo',
	forceSelection : true,
	store : storeAfectadoApoyo,
	emptyText : 'Atender a',
	triggerAction : 'all',
	editable : false,
	displayField : 'Nombre',
	valueField : 'KyAfectado',
	allowBlank : false,
	mode : 'local',
	blankText : 'Atender a',
	width : 450
});

var TxtSolNombres = new Ext.form.TextField({
	fieldLabel : 'Nombres',
	name : 'TxtSolNombres',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtSolAppaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Paterno',
	name : 'TxtSolAppaterno',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtSolApmaterno = new Ext.form.TextField({
	fieldLabel : 'Ap.Materno',
	name : 'TxtSolApmaterno',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtCargo = new Ext.form.TextField({
	fieldLabel : 'Cargo',
	name : 'TxtCargo',
	readOnly : false,
	style : 'color:#006600;',
	width : 350
});

var TxtInstitucion = new Ext.form.TextField({
	fieldLabel : 'Institucion',
	name : 'TxtInstitucion',
	readOnly : false,
	style : 'color:#006600;',
	width : 600
});

var TxtInforme = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtInforme',
	readOnly : false,
	width : 780,
	height : 75
});

var TxtOtros = new Ext.form.HtmlEditor({
	hideLabel : true,
	name : 'TxtOtros',
	readOnly : false,
	width : 780,
	height : 75
});

var FieldGrupoExpediente01 = new Ext.form.FieldSet({
	title : 'Expediente',
	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKyExpediente, TxtNroExpediente, txtFecha, txtLugar ]
});

var FieldGrupoSolicitud02 = new Ext.form.FieldSet({
	title : 'Derivacion',
	collapsible : false,
	autoHeight : true,
	defaultType : 'textfield',
	items : [ txtKy, CboDeriva, comboAfectadoApoyo, comboTipoApoyo, TxtSolNombres,
			TxtSolAppaterno, TxtSolApmaterno, TxtCargo, TxtInstitucion, TxtInforme,
			TxtOtros, txtFechaSolicitud ]
});

function AbrirSolictudes() {
	if (!winNuevaSolicitud) {
		winNuevaSolicitud = new Ext.Window({
			layout : 'form',
			title : 'Nuevo/Editar Ficha de Derivacion',
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
			items : [ FieldGrupoExpediente01, FieldGrupoSolicitud02 ],
			tbar : [ '->', btnCerrar ],
			bbar : [ '->', btGuardar ],
			listeners : {
				show : function() {
					storeAfectadoApoyo.load({
						params : {
							expediente : txtKyExpediente.getValue()
						}
					});
				}
			}
		});
	}
	winNuevaSolicitud.show();
}

function limpiarControles() {
	txtFechaSolicitud.reset();
	comboTipoApoyo.reset();
	TxtCargo.reset();
	TxtInstitucion.reset();
	TxtInforme.reset();
	TxtOtros.reset();
	TxtSolNombres.reset();
	TxtSolAppaterno.reset();
	TxtSolApmaterno.reset();
	txtKy.reset();
	txtKy.setValue('00');
}