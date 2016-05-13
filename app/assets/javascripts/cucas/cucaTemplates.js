(function() {

    App.Cucas.Templates = {
        _init: function() {
            App.Cucas.Templates.ListItemTemplate = $('#cucas-list-item-template').html();
            Mustache.parse(App.Cucas.Templates.ListItemTemplate);
            App.Cucas.Templates.ListItemEditTemplate = $('#cucas-list-item-edit-template').html();
            Mustache.parse(App.Cucas.Templates.ListItemEditTemplate);
            App.Cucas.Templates.ItemTemplate = $('#cucas-item-template').html();
            Mustache.parse(App.Cucas.Templates.ItemTemplate);
        }
    };

    App.Cucas.Templates._init();
})();
