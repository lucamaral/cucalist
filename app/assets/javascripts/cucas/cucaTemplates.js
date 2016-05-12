(function() {

    App.Cucas.Templates = {
        _init: function() {
            App.Cucas.Templates.ListItemTemplate = $('#cucas-list-item-template').html();
            Mustache.parse(App.Cucas.Templates.ListItemTemplate);
        }
    };

    App.Cucas.Templates._init();
})();
