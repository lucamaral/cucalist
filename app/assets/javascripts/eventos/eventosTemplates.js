(function() {

    App.Eventos.Templates = {
        _init: function() {
            App.Eventos.Templates.ListItemTemplate = $('#evento-list-template').html();
            Mustache.parse(App.Eventos.Templates.ListItemTemplate);
            App.Eventos.Templates.ItemTemplate = $('#evento-item-template').html();
            Mustache.parse(App.Eventos.Templates.ItemTemplate);
        }
    };

    App.Eventos.Templates._init();
})();
