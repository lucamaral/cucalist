(function() {
    App.Cucas.View = {
        limparLista: function() {
            $('#cucas-list').empty();
        },
        renderizarItem: function(cuca) {
            var rendered = Mustache.render(App.Cucas.Templates.ListItemTemplate, cuca);
            $('#cucas-list').append(rendered);
        },
        bindNovaCuca: function(novaCucaCallback) {
            $("#nova-cuca-form").submit(function(event) {
                event.preventDefault();
                event.stopPropagation();

                var tipo = $("#tipo-nova-cuca-input").val();
                var padaria = $("#padaria-nova-cuca-input").val();
                var cuca = {
                    "tipo": tipo,
                    "origem": padaria
                };

                novaCucaCallback(cuca);
            });
        }
    };
})();
