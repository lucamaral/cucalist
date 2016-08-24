(function() {

    App.Eventos.Templates = {
        _init: function() {
            App.Eventos.Templates.EventoListTemplate = $('#evento-list-template').html();
            Mustache.parse(App.Eventos.Templates.EventoListTemplate);
            App.Eventos.Templates.EventoNovoTemplate = $('#evento-novo-template').html();
            Mustache.parse(App.Eventos.Templates.EventoNovoTemplate);
            App.Eventos.Templates.EventoDetalhesTemplate = $('#evento-detalhes-template').html();
            Mustache.parse(App.Eventos.Templates.EventoDetalhesTemplate);
            App.Eventos.Templates.EventoEditarTemplate = $('#evento-edit-template').html();
            Mustache.parse(App.Eventos.Templates.EventoEditarTemplate);
        }
    };

    App.Eventos.Templates._init();
})();
