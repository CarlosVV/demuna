
Ext.onReady(function() {
    var PersonRecord = Ext.data.Record.create([
        {name: 'Materia',    type: 'string'},       
        {name: 'Meses',  type: 'string'},
		{name: 'Casos', type: 'string'},
    ]);
    //url:'aarepordemunasmesesmaterias.php?cargar=true&demuna='+txtKyDemunaIni.getValue(),
	var storedemunas = new Ext.data.JsonStore({
					url:'reportes/aarepordemunasmesesmaterias.htm',
					root: 'data',
					totalProperty: 'total',
					paramNames: {
						start : 'offset',  
						limit : 'size',  
						sort : 'sort',   
						dir : 'dir'      
					},
					fields: ['Materia','Casos','Meses']
				});		
	storedemunas.load();		
		          
    var perDecade = new Ext.grid.PivotGrid({
        title     : '#Casos por Meses y Materias en esta Demuna',
        width     : 1000,
        height    : 500,
        renderTo  : 'DivformExpediente',
        store     : storedemunas,
		aggregator: 'sum',
   		measure   : 'Casos',
        topAxis: [
            {
                width: 80,
                dataIndex: 'Meses'
            }
        ],
        
        leftAxis: [
            {
				 width: 200,	
                dataIndex: 'Materia'
            }
        ]
    });
    
    
});
		