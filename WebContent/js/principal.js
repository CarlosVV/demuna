/*!
 * Ext JS Library 3.4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.onReady(function(){
    Ext.QuickTips.init();
   
var menuSistema = new Ext.menu.Menu({
        id: 'mainMenuSistema',		      
        items: [ {
                text: 'Pantalla Inicial', 
				handler: function()
									{  									 
									document.location="?pagin=acces";
									}
            },'-',{
                text: 'Cerrar Sesion', 
				handler: function()
									{  									 
									document.location="logout.htm";
									}
            }
        ]
    });

									  													
 var menuUsuarios = new Ext.menu.Menu({
        id: 'mainMenuUser',		      
        items: [ {
                text: 'Cambiar mi contraseña',
				handler: function()
				{  									 
					abrirLoginUpdate();
				}				
            },{
                text: 'Actualizar mis datos personales', 
				handler: function()
				{  									 
					document.location="?pagin=usrs";
				}	
            },{ 
				text: 'Crear Usuarios',
				handler: function()
				{ 					
					document.location="?pagin=nwusrs";					
				}				
            }
        ]
    });
 
 var menuMantenimientos = new Ext.menu.Menu({
        id: 'mainMenuMante',		       
        items: [ {
                text: 'Crear Dependencias', 
				handler: function()
									{  									 
									document.location="?pagin=dmns";
									}
            },'-',{
                text: 'Crear Materias', 
				handler: function()
									{  									 
									document.location="?pagin=mtrs";
									}
            },'-',{
                text: 'Discapacidades', 
				handler: function()
									{  									 
									document.location="?pagin=dscp";
									}
            }
        ]
    });
 
 var menuOperaciones = new Ext.menu.Menu({
        id: 'mainMenuOper',		       
        items: [ {
                text: '1. Recepción de Expedientes',
				handler: function()
									{  									 
									document.location="?pagin=expdts";
									}
            },{
                text: '2. Generar Derivaciones',
				handler: function()
									{  									 
									document.location="?pagin=slctds";
									}
            },{
                text: '3. Ficha de Entrevista',
				handler: function()
									{  									 
									document.location="?pagin=ntrvsts";
									}
            },{
                text: '4. Acta de Conciliación',
				handler: function()
									{  									 
									document.location="?pagin=cnclcn";
									}
            },{
                text: '5. Acta de Compromiso',
				handler: function()
									{  									 
									document.location="?pagin=cmprms";
									}
            },{
                text: '6. Informe de Verificación', 
				handler: function()
									{  									 
									document.location="?pagin=vrfcn";
									}
            },{
                text: '7. Seguimiento de Expedientes  (Pendiente)',
                handler: function()
				{  									 
                					//document.location="?pagin=vrfcn";
				}
            }
        ]
    });
 
 
 
  var menuReportes = new Ext.menu.Menu({
        id: 'mainMenuReport',		       
        items: [ {
                text: 'Reporte por Materias en esta Demuna', 
				handler: function()
									{  									 
									document.location="?pagin=rptdmn";
									}
            },{
                text: 'Reporte de #Casos por Meses y Materias en esta Demuna', 
				handler: function()
									{  									 
									document.location="?pagin=rptdmnmssmtr";
									}
            },{
                text: 'Reporte de #Casos por Meses en esta Demuna', 
				handler: function()
									{  									 
									document.location="?pagin=rptdmnmss";
									}
            },{
                text: 'Reporte de #Casos por Demuna', 
				handler: function()
									{  									 
									document.location="?pagin=rptalldmn";
									}
            },'-',{
                text: 'Grafico por Materias en esta Demuna', 
				handler: function()
									{  									 
									document.location="?pagin=ocrsdmn";
									}
            },'-',{
                text: 'Reporte Anual Excel', 
				handler: function()
									{  									 
									document.location="?pagin=xclyear";
									}
            }
			
        ]
    });
  
  
  
 var tb = new Ext.Toolbar({
						  title:'Sistema'
						  });
    tb.render('toolbar'); 		
    tb.add({
            text:'Sistema',
			iconAlign: 'top',
            icon: './icon/sistema.png',  						
            menu: menuSistema  
        },'->',{
            text:'Usuarios',
			iconAlign: 'top',
            icon: './icon/status.png',  						
            menu: menuUsuarios  
        },{
            text:'Mantenimiento',
			iconAlign: 'top',
            icon: './icon/configure.png',  // <-- icon							
            menu: menuMantenimientos  
        },{
            text:'Procedimientos',
			iconAlign: 'top',
			icon: './icon/exec.png',          			
            menu: menuOperaciones  
        },{
            text:'Reportes y Estadistica',
			iconAlign: 'top',
			icon: './icon/new.png',          			
            menu: menuReportes
        }
		);
      
    tb.doLayout();	
 					

});