Ext.onReady(function() {
	
	var store2 = new Ext.data.TreeStore({
        model: 'Item',
        root: {
            text: 'Root 2',
            expanded: true,
            children: [{
                text: 'Folder 1',
                children: [{
                    text: 'Child 1',
                    canDropOnFirst: true,
                    canDropOnSecond: true,
                    leaf: true
                }, {
                    text: 'Child 2',
                    canDropOnFirst: true,
                    canDropOnSecond: true,
                    leaf: true
                }],
                expanded: true
            }, {
                text: 'Folder 2',
                children: [],
                expanded: true
            }]
        }
    });
	new Ext.panel.Panel({
        renderTo: 'west',
        width: 300,
        height: 200,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        defaultType: 'treepanel',
        defaults: {
            rootVisible: false,
            flex: 1
        },
        items: [ {
            title: 'Destination',
            store: store2,
            viewConfig: {
                plugins: {
                   ptype: 'treeviewdragdrop',
                   enableDrag: false,
                   enableDrop: true,
                   appendOnly: true
                },
                listeners: {
                    nodedragover: function(targetNode, position, dragData){
                        var rec = dragData.records[0],
                            isFirst = targetNode.isFirst(),
                            canDropFirst = rec.get('canDropOnFirst'),
                            canDropSecond = rec.get('canDropOnSecond');
                            
                        return isFirst ? canDropFirst : canDropSecond;
                    }
                }
            }
        }]
    });
});