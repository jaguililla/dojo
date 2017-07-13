
$(function () {
    var eventBus = new EventBus('http://localhost:9191/eventbus');

    eventBus.onopen = function () {
        eventBus.registerHandler('message.example', function (error, message) {
            alert('Connected');
        });
    };

    $('#btnSend').bind('click', function () {
        alert("click");
        eventBus.publish ('message.example', { text: 'txt' });
    });
});
