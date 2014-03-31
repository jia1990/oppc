Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.layout.container.HBox',
    'Ext.window.MessageBox'
]);

Ext.define('Item', {
    extend: 'Ext.data.Model',
    fields: ['text', 'canDropOnFirst', 'canDropOnSecond']
});

Ext.onReady(function() {
    

	var tree_store = new Ext.data.TreeStore({
		id:'tree_store',
		proxy: {
			type: 'ajax',
			url: 'TreeData.sp',
			reader: {
				type: 'json'
			}
		},
		root: { expanded: false },//
		autoLoad: false
	}); 
	var treePanel = Ext.create('Ext.tree.Panel', {
		//layout:'fit',
		stateId: 'navigation-panel',
		id: 'tree-panel',
		title: '拓扑结构',
		region: 'west',
		split: true,
		width: 200,
		minWidth: 175,
		maxWidth: 400,
		animCollapse: true,
		rootVisible: false,  //默认不显示根节点
		//singleExpand: true, //
		//containerScroll:true,
		useArrows: true,
		store: tree_store,
		//root: {expanded: true},
		tools: [{
			type: 'refresh',
			tooltip: '刷新',
			// hidden:true,
			handler: function() {//Ext.data.Store load
				tree_store.load({
					scope: this,
					callback: function(records, operation, success) {
						//Ext.Msg.alert('refresh success!');
						treePanel.getRootNode().eachChild(function(child) { child.expand(); });
					}
				});
			}
		}
		],
		collapsible: true,
		listeners: {
			'load' : function(){
				treePanel.expandAll();
			}, 

			'itemclick': function(view, record, item, index, e) {
				nodeId = record.raw.id; //获取点击的节点id
				nodeText = record.raw.text; //获取点击的节点text
				//Ext.Msg.alert('info', nodeId + nodeText);
				if (tree_store.getNodeById(nodeId).hasChildNodes() === true) {
					ReloadMainCollectionGridTab(nodeId, 'QB', Ext.getCmp('datepicker').getRawValue(), 1, 0, 0, 1, 0, 0, 0, 0, nodeText); //
				}
				else {
					LoadConcentratorInfoStore(nodeId);
					Ext.Msg.alert('设备管理', nodeId + nodeText);
				}
				//Ext.Msg.alert('text', nodeText);

			}
		}
	});
	treePanel.render('tree-div');  
});
