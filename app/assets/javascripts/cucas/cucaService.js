(function() {

    App.Cucas.Service = {
        list: function() {
            return $.ajax({
                type: 'GET',
                url: '/api/cucas',
                dataType: 'json'
            });
        },
        nova: function(novaCuca) {
            return $.ajax({
                type: 'POST',
                url: '/api/cucas',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(novaCuca)
            });
        }
    };

})();
