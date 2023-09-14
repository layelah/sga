/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    eventClick: function(info) {
      var eventObj = info.event;

      if (eventObj.url) {
        alert(
          'Clicked ' + eventObj.title + '.\n' +
          'Will open ' + eventObj.url + ' in a new tab'
        );

        window.open(eventObj.url);

        info.jsEvent.preventDefault(); // prevents browser from following link in current tab.
      } else {
        alert('Clicked ' + eventObj.title);
      }
    },
    initialDate: '2021-06-15',
    events: [
      {
        title: 'simple event',
        start: '2021-06-02'
      },
      {
        title: 'event with URL',
        url: 'https://www.google.com/',
        start: '2021-06-03'
      }
    ]
  });

  calendar.render();
});


