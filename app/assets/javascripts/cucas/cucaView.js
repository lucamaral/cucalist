(function() {
    App.Cucas.View = {
        limparLista: function() {
            $('#cucas-list').empty();
        },
        renderizarItems: function(cucasInput) {
            var rendered = Mustache.render(App.Cucas.Templates.ListItemTemplate, cucasInput);
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
        },
        bindPesquisaCuca: function(pesquisaCallback){
          $("#cuca-search-button").click(function(){

            var searchTerm = $("#cucas-search-input").val();

            pesquisaCallback(searchTerm);
          })
        }
    };
})();
