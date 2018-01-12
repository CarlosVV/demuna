
		var winUsuarios;							
			// Cajas de Textos	
			
				var strData = new Ext.data.JsonStore({
				url:'frmusuariosupdate/cargar.htm',
				root: 'data',
				totalProperty: 'total',
				paramNames: {
					start : 'offset',
					limit : 'size',
					sort : 'sort',
					dir : 'dir'
				},
				fields: ['Nombres','Casos']
			});
	
		strData.load();
		
		
		var btnAceptarUsuarios = new Ext.Button({
		    id: 'btnAceptarUsuarios',
			name:'btnAceptarUsuarios',
			x: 380,
			y: 100,
			iconAlign: 'top',					
			scale: 'large',
			text :'Actualizar',
			icon:'icon/ok111.png',
			minWidth: 80,			
			handler:function(){
				frmUsuarioUp.validarAcceso();
			} 
		});
																		
		var frmUsuarioUp = new Ext.FormPanel({ 
			frame:false, 		
			layout: 'form',	
			height: 420,
			bodyStyle:'padding:20px;',
			items:[txtKyUsuario,txtKyDemunaIni, txtNombres,txtAppaterno,txtApmaterno, txtTelefonos, txtCorreo],		
			validarAcceso: function(){				
				if (this.getForm().isValid()) {
					this.getForm().submit({
						url: 'frmusuariosupdate/editarusuario.htm',
						method: 'POST',
						waitTitle: 'Conectando',
						waitMsg: 'Validando Identificaci&oacute;n..',
						success: function(form, action){
							
							Ext.MessageBox.confirm('Datos Actualizados!','para aplicar los cambios debe reiniciar la sesion. Quiere hacerlo?',					
							function(btn){
								if(btn =='yes')
								{																				
									winUsuarios.hide();
									document.location="?pagin=close";						
								}
								else
								{																				
									winUsuarios.hide();													
								}
							});		
																  
								
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
		
	
		function abrirUsuarioUpdate(){		
			if (!winUsuarios) {
				winUsuarios = new Ext.Panel({
					layout: 'form',
					autoWidth:true,
					height: 500,		
					title: 'Actualizar Datos Personales',			
					resizable: false,
					renderTo:'DivformExpediente',
					closeAction: 'hide',
					closable: true,
					draggable: true,
					plain: true,
					border: false,
					modal:false,										
					items: [frmUsuarioUp],
						bbar:['->',btnAceptarUsuarios],
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
			
			winUsuarios.show();
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
