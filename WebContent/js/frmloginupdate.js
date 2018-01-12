/**
 * 
 * Todas las cuestiones de uso de la libreria quedan sujetas a los terminos de ExtJs Library
 * para mayor información visite http://extjs.com/license
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 *
 */
	var winLogin;							
		// Cajas de Textos	
		var txtUsuariolog = new Ext.form.TextField({
				name: 'usr',
				fieldLabel:'Usuario',	
				width: 150,	
				blankText: 'Usuario Requerido.',
				allowBlank: false,				
				enableKeyEvents: true,
				selectOnFocus: true,
                                disabled:true,
				listeners: {
					keypress: function(t,e){
						if(e.getKey()==13){
							txtClave.focus();
						}
					}
				}
		});
						
		var txtClave = new Ext.form.TextField({
				name: 'clave',
				fieldLabel:'Clave Actual',
				inputType:'password', 
				width: 100,				
				allowBlank: false,
				blankText: 'Clave de acceso requerida.',
				enableKeyEvents: true,
				selectOnFocus: true,
				listeners: {
					keypress: function(t,e){
						if(e.getKey()==13){
							txtClaveNueva.focus();
						}
					}
				}
		});
		
		var txtClaveNueva = new Ext.form.TextField({
				name: 'txtClaveNueva',
				fieldLabel:'Clave Nueva',
				inputType:'password', 
				width: 100,				
				allowBlank: false,
				blankText: 'Clave Nueva requerida.',
				enableKeyEvents: true,
				selectOnFocus: true,
				listeners: {
					keypress: function(t,e){
						if(e.getKey()==13){
							txtClaveConfirm.focus();
						}
					}
				}
		});
		
		var txtClaveConfirm = new Ext.form.TextField({
				name: 'txtClaveConfirm',
				fieldLabel:'Confirma Clave',
				inputType:'password', 
				width: 100,				
				allowBlank: false,
				blankText: 'Confirmacion Clave requerida.',
				enableKeyEvents: true,
				selectOnFocus: true,
				listeners: {
					keypress: function(t,e){
						if(e.getKey()==13){
							btnAceptar.focus();
						}
					}
				}
		});
		
		var fieldgrupoclave01 = new Ext.form.FieldSet({
				title: 'Usuario y Clave Actual',
				collapsible: false,
				autoHeight:true,				
				defaultType: 'textfield',
				items :[txtUsuariolog, txtClave]						
			});
	
	
		var fieldgrupoclave02 = new Ext.form.FieldSet({
					title: 'Clave Nueva',
					collapsible: false,
					autoHeight:true,				
					defaultType: 'textfield',
					items :[txtClaveNueva, txtClaveConfirm]						
				});
		
		var btnAceptar = new Ext.Button({
		    id: 'btnAceptar',
			name:'btnAceptar',
			x: 380,
			y: 100,
			iconAlign: 'top',					
			scale: 'large',
			text :'Actualizar',
			icon:'icon/ok111.png',
			minWidth: 80,			
			handler:function(){
				frmLoginUp.validarAcceso();
			} 
		});
																		
		var frmLoginUp = new Ext.FormPanel({ 
			frame:false, 		
			layout: 'form',	
			height: 350,
			bodyStyle:'padding:20px;',
			items:[fieldgrupoclave01,fieldgrupoclave02],		
			validarAcceso: function(){				
				if (this.getForm().isValid()) {
					this.getForm().submit({
						url: 'loginupdate.htm',
						method: 'POST',
						waitTitle: 'Conectando',
						waitMsg: 'Validando Identificaci&oacute;n..',
						success: function(form, action){
							    Ext.Msg.alert('Exito', 'Actualizada la Clave de Acceso');
								winLogin.hide();							
						},
						failure: function(form, action){
							if (action.failureType == 'server') {
								var data = Ext.util.JSON.decode(action.response.responseText);
								Ext.Msg.alert('Error al Conectar', data.errors.reason, function(){
																	
								});
							}
							else {
								Ext.Msg.alert('Error!', 'El servidor de autenticacion es inalcanzable : ' + action.response.responseText);
							}
							txtUsuariolog.focus(true, 100);	
						}
					});
				}
				else
				{					
					Ext.MessageBox.show
							({  
								title: 'Validaci&oacute;n',  
								msg: 'Los controles marcados no tienen datos o tienen datos invalidos.',  
								buttons: Ext.MessageBox.OK,  
								icon: Ext.MessageBox.ERROR  
							});	
							
				}
			}
		});
				
		function abrirLoginUpdate(){		
			if (!winLogin) {
				winLogin = new Ext.Window({
					layout: 'form',
					width: 400,
					height: 300,		
					title: 'Actualizar Clave',			
					resizable: false,
					closeAction: 'hide',
					closable: true,
					draggable: true,
					plain: true,
					border: false,
					modal:true,										
					items: [frmLoginUp],
						bbar:['->',btnAceptar],
					listeners: {
						hide: function(){
							var frm = frmLoginUp.getForm();
							frm.reset();
							frm.clearInvalid();
						},
						show: function(){
							txtUsuariolog.focus(true, 100);
						}
					}
				});
			}
			
			winLogin.show();
		}
		
		Ext.onReady(function(){			
		//Ext.BLANK_IMAGE_URL = '../imagenes/s.gif';
		Ext.QuickTips.init();
		/*	//Evento onclick del boton Abrir login.
			var button = Ext.get('mostrar-btn');
			button.on('click', function(){
				abrirLogin();
			});*/
			
		});
