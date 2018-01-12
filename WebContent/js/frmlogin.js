/**
 * 
 * Todas las cuestiones de uso de la libreria quedan sujetas a los terminos de
 * ExtJs Library para mayor información visite http://extjs.com/license
 * Copyright(c) 2006-2009, Ext JS, LLC. licensing@extjs.com
 * 
 */
var winLogin;
// Cajas de Textos
var txtUsuariolog = new Ext.form.TextField(
		{
			name : 'usr',
			hideLabel : true,
			width : 280,
			x : 10,
			y : 25,
			allowBlank : false,
			blankText : 'Email de usuario requerido.',
			enableKeyEvents : true,
			selectOnFocus : true,
			vtypeText : 'Utilice un formato de Email v&aacute;lido usuario@proveedor.ejm',
			listeners : {
				keypress : function(t, e) {
					if (e.getKey() == 13) {
						txtClave.focus();
					}
				}
			}
		});

var txtClave = new Ext.form.TextField({
	name : 'clave',
	hideLabel : true,
	inputType : 'password',
	width : 180,
	x : 10,
	y : 75,
	allowBlank : false,
	blankText : 'Clave de acceso requerida.',
	enableKeyEvents : true,
	selectOnFocus : true,
	listeners : {
		keypress : function(t, e) {
			if (e.getKey() == 13) {
				btnAceptary.focus();
			}
		}
	}
});

// Labels
var lblUsuario = new Ext.form.Label({
	text : 'Usuario :',
	x : 10,
	y : 10,
	height : 20,
	cls : 'x-label'
});

var lblClave = new Ext.form.Label({
	text : 'Clave :',
	x : 10,
	y : 55,
	height : 20,
	cls : 'x-label'
});

// botones
var btnAceptary = new Ext.Button({
	id : 'btnAceptary',
	name : 'btnAceptary',
	x : 380,
	y : 100,
	iconAlign : 'top',
	scale : 'large',
	text : 'Ingresar...',
	icon : 'icon/ok111.png',
	minWidth : 80,
	// disabled:true,
	handler : function() {
		frmLogin.validarAcceso();
	}
});

var frmLogin = new Ext.FormPanel(
		{
			frame : false,
			layout : 'absolute',
			items : [ lblUsuario, lblClave, txtUsuariolog, txtClave ],
			bbar : [ '->', btnAceptary ],
			validarAcceso : function() {
				if (this.getForm().isValid()) {
					this
							.getForm()
							.submit(
									{
										url : 'login.htm',
										method : 'POST',
										waitTitle : 'Conectando',
										waitMsg : 'Validando Identificaci&oacute;n..',
										success : function(form, action) {
											document.location = "index.htm?pagin=acces";
											winLogin.hide();
										},
										failure : function(form, action) {
											if (action.failureType == 'server') {
												var data = Ext.util.JSON
														.decode(action.response.responseText);
												Ext.Msg
														.alert(
																'Error al Conectar',
																data.errors.reason,
																function() {
																	txtUsuariolog
																			.focus(
																					true,
																					100);
																});
											} else {
												Ext.Msg
														.alert(
																'Error!',
																'El servidor de autenticacion es inalcanzable : '
																		+ action.response.responseText);
											}
											txtUsuariolog.focus(true, 100);
										}
									});
				} else {
					Ext.MessageBox
							.show({
								title : 'Validaci&oacute;n',
								msg : 'Los controles marcados no tienen datos o tienen datos invalidos.',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});

				}
			}
		});

function abrirLogin() {
	if (!winLogin) {
		winLogin = new Ext.Window({
			layout : 'fit',
			width : 400,
			height : 200,
			title : 'Iniciar Sesi&oacute;n',
			resizable : false,
			closeAction : 'hide',
			closable : false,
			draggable : true,
			plain : true,
			border : false,
			modal : true,
			items : [ frmLogin ],
			listeners : {
				hide : function() {
					var frm = frmLogin.getForm();
					frm.reset();
					frm.clearInvalid();
				},
				show : function() {
					txtUsuariolog.focus(true, 100);
				}
			}
		});
	}

	winLogin.show();
}

Ext.onReady(function() {
	// Ext.BLANK_IMAGE_URL = '../imagenes/s.gif';
	Ext.QuickTips.init();
	/*
	 * //Evento onclick del boton Abrir login. var button =
	 * Ext.get('mostrar-btn'); button.on('click', function(){ abrirLogin(); });
	 */

});
