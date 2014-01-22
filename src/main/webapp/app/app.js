Ext.require(['Ext.data.*', 'Ext.grid.*']);

Ext.define('Product', {
    extend: 'Ext.data.Model',
    fields: [{
            name: 'id',
            type: 'int',
            useNull: true
        }, 'name', 'price'],
    validations: [{
            type: 'length',
            field: 'name',
            min: 1
        }, {
            type: 'length',
            field: 'price',
            min: 1
        }]
});

Ext.onReady(function() {

    var store = Ext.create('Ext.data.Store', {
        autoLoad: true,
        autoSync: true,
        model: 'Product',
        proxy: {
            type: 'rest',
            url: 'products',
            format: 'json',
            headers: {
                'Content-Type': 'application/json'
            },
            reader: {
                type: 'json',
                root: 'data'
            },
            writer: {
                type: 'json'
            },
            api: {
                create: 'products/create/',
                read: '',
                update: 'products/edit/',
                destroy: 'products/delete/'
            }
        }
    });

    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing');

    var grid = Ext.create('Ext.grid.Panel', {
        renderTo: document.body,
        plugins: [rowEditing],
        width: 400,
        height: 300,
        frame: true,
        title: 'Products',
        store: store,
        columns: [{
                text: 'ID',
                width: 40,
                sortable: true,
                dataIndex: 'id'
            }, {
                text: 'Name',
                flex: 1,
                sortable: true,
                dataIndex: 'name',
                field: {
                    xtype: 'textfield'
                }
            }, {
                header: 'Price',
                width: 80,
                sortable: true,
                dataIndex: 'price',
                field: {
                    xtype: 'textfield'
                }
            }],
        dockedItems: [{
                xtype: 'toolbar',
                items: [{
                        text: 'Add',
                        iconCls: 'icon-add',
                        handler: function() {
                            // empty record
                            store.insert(0, new Product());
                            rowEditing.startEdit(0, 0);
                        }
                    }, '-', {
                        text: 'Delete',
                        iconCls: 'icon-delete',
                        handler: function() {
                            var selection = grid.getView().getSelectionModel().getSelection()[0];
                            if (selection) {
                                store.remove(selection);
                            }
                        }
                    }]
            }]
    });
});