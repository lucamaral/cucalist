(function() {
    App.Cucas.View = {
        limparLista: function() {
            $('#cucas-list').empty();
        },
        renderizarItems: function(cucasInput, callback) {
            var rendered = Mustache.render(App.Cucas.Templates.ListItemTemplate, cucasInput);
            $('#cucas-list').append(rendered);
            var el1 = document.querySelectorAll('#voto');
            var el2 = document.querySelectorAll('#cuca-rating');
            App.MakeRating.makeRating(el1, callback, cucasInput, 1);
            App.MakeRating.makeRating(el2, function(){return true;}, cucasInput, 2);
        },
        bindNovaCuca: function(novaCucaCallback) {
            $(".nova-cuca-form").submit(function(event) {
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
        },

        bindRemoverCuca: function(removeCallback){
          $('.cucas-button-remover').off();
          $('.cucas-button-remover').click(function(event) {
              var id = $(event.target).closest('li').data("id");
              removeCallback(id);
          });
        },

        bindEditarCuca: function(editCallback){
          $('.cucas-button-edit').off();
          $('.cucas-button-edit').click(function(evento){
              var li = $(event.target).closest('li');
              var tipo = li.data("tipo");
              var origem = li.data("origem");
              var id = li.data('id');
              li.empty();
              var cuca= {
                "id": id,
                "tipo": tipo,
                "origem": origem
              };
              var rendered = Mustache.render(App.Cucas.Templates.ListItemEditTemplate, cuca);
              li.append(rendered);

              li.find('.cucas-edit-salvar-button').click(function(evento){
                var tipoEditado = li.find('.tipo-cuca-input').val();
                var origemEditado = li.find('.origem-cuca-input').val();
                var cucaEditada= {
                  "id": id,
                  "tipo": tipoEditado,
                  "origem": origemEditado
                }
                editCallback(true, cucaEditada);
              });

              li.find('.cucas-edit-cancelar-button').click(function(evento){
                editCallback(false, cuca);
              });
          });
        },

        renderizarItem: function(cuca, callback){
          var rendered = Mustache.render(App.Cucas.Templates.ItemTemplate, cuca);
          var li = $("[data-id=" + cuca.id +"]");
          li.data("tipo", cuca.tipo);
          li.data("origem", cuca.origem);
          li.empty();
          li.append(rendered);
          var el1 = li.find('#voto');
          var el2 = li.find('#cuca-rating');
          App.Cucas.View.makeRating(el1, callback, cuca, 1);
          App.Cucas.View.makeRating(el2, callback, cuca, 2);
        }
    };
})();
