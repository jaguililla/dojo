
$(function () {
    var eventBus = new EventBus('http://localhost:3210/eventbus');

    eventBus.onopen = function () {
        eventBus.registerHandler('messages.example', function (error, message) {
            alert('Connected');
        });
    };

    $('#btnSend').bind('click', function () {
        eventBus.publish ('messages.example', { text: 'txt' });
    });
});
