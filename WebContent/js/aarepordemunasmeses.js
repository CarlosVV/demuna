
Ext.onReady(function() {
    var PersonRecord = Ext.data.Record.create([
        {name: 'Demuna',    type: 'string'},       
        {name: 'Meses',  type: 'string'},
		{name: 'Casos', type: 'string'},
    ]);
    
  //'aarepordemunasmeses.php?cargar=true&demuna='+txtKyDemunaIni.getValue(),
	var storedemunas = new Ext.data.JsonStore({
					url:'reportes/aarepordemunasmeses.htm', 
					root: 'data',
					totalProperty: 'total',
					paramNames: {
						start : 'offset',  
						limit : 'size',  
						sort : 'sort',   
						dir : 'dir'      
					},
					fields: ['Demuna','Casos','Meses']
				});		
	storedemunas.load();		
		          
    var perDecade = new Ext.grid.PivotGrid({
        title     : '#Casos por Meses en esta Demuna',
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
                dataIndex: 'Demuna'
            }
        ]
    });
    
    
});
		