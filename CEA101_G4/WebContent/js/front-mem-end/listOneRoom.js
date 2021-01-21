	// calendar
	function isLeapYear(year) {
	    if (year % 4 !== 0) {
	        return false
	    } else if (year % 400 === 0) {
	        return true
	    } else if (year % 100 === 0) {
	        return false
	    } else {
	        return true
	    }
	}
	
    Date.prototype.yyyymmdd = function() {
        var mm = this.getMonth() + 1 // getMonth is zero-based
        var dd = this.getDate()

        return [this.getFullYear(), (mm > 9 ? '' : '0') + mm, (dd > 9 ? '' : '0') + dd].join('-')
    }

    let isPassedDate = function(compareDate) {
        let today = new Date()
        if (compareDate.getFullYear() < today.getFullYear()) {
            return true
        }

        if (compareDate.getFullYear() === today.getFullYear() && compareDate.getMonth() < today.getMonth()) {
            return true
        }

        if (
            compareDate.getFullYear() === today.getFullYear() &&
            compareDate.getMonth() === today.getMonth() &&
            compareDate.getDate() < today.getDate()
        ) {
            return true
        }
        return false
    }

    let isCurrentDate = function(compareDate) {
        let today = new Date()
        if (compareDate.getFullYear() == today.getFullYear() &&
            compareDate.getMonth() == today.getMonth() &&
            compareDate.getDate() == today.getDate()) {
            return true
        }
        return false
    }
    
    let delCal = function(firstDateOfMonth, callback) {
        $('tbody').html('')
        callback(firstDateOfMonth)
    }
    
    let orderedList = $('[name="roomOrderedDateList"]').val().split(/\, |\[|\]/);
    let drawWithTarget = function(firstDateOfMonth, targetCal) {
        let monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        let monthList = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JLY', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    	let tr, td, a
        let startFrom = firstDateOfMonth.getDay()

        let day = 0
        let i = 0
        let selectedMonthDays = monthDays[firstDateOfMonth.getMonth()]

        // 判斷閏年2月
        if (firstDateOfMonth.getMonth() === 1) {
            if (isLeapYear(firstDateOfMonth.getFullYear())) {
                selectedMonthDays = 29
            }
        }
		
//    	console.log($('.' + targetCal + ' .mon'));
    	$('.' + targetCal + ' .mon').html(monthList[firstDateOfMonth.getMonth()]);
    	$('.' + targetCal + ' .year').html(firstDateOfMonth.getFullYear());
        let tb = $('.' + targetCal + ' tbody')[0]
        // 迴圈停止條件: 該行滿7格，且日期已填完
        while (day < selectedMonthDays || i % 7 !== 0) {

            tr = document.createElement('tr')
            for (let j = 0; j < 7; j++) {
                a = document.createElement('a');
                td = document.createElement('td')
                if (i >= startFrom && day < selectedMonthDays) {
                    day++
                    a.innerText = day.toString()
                    let setDate = new Date(firstDateOfMonth.getFullYear(), firstDateOfMonth.getMonth(), day)
                    td.setAttribute('value', setDate.yyyymmdd())
//                    a.setAttribute('href', '#')
                    if (isPassedDate(setDate)) {
                        td.classList.add('passedDate')
                    } else if (isCurrentDate(setDate)) {
                        td.classList.add('currentDate');
                    }
                    
                    if(orderedList.indexOf(setDate.yyyymmdd()) > -1) {
                    	td.classList.add('orderedDate')
                    }
                    
                }
                td.appendChild(a)
                tr.appendChild(td)
                i++
            }
            tb.appendChild(tr)
        }
    }

    let drawCal = function(firstDateOfMonth) {
    	drawWithTarget(firstDateOfMonth, 'currentCalendar');
    	firstDateOfMonth.setMonth(firstDateOfMonth.getMonth()+1);
    	
    	drawWithTarget(firstDateOfMonth, 'nextCalendar');
    }
	
	$(document).ready(function() {
//		let orderedListStr = $('[name="roomOrderedDateList"]').val();
//		console.log(orderedList)
		
		
		
		let today = new Date();
		let currentStart = today;
		var contextPath = $('[name="contextPath"]').val();
        var urlTarget = contextPath + '/room/room.do';
        
        delCal(new Date(today.getFullYear(), today.getMonth(), 1), drawCal)
        
        $(document).on('click', '.calendarBody tbody td:not(.passedDate)', function() {
        	let onSelectList = $('.onSelect');
        	let orderRangeList = $('.orderedRange');
        	let sel = $(this);
        	if (orderRangeList.length > 0) {
        		$('.calendarBody tbody td').removeClass('orderedRange');
        	}
        	if (onSelectList.length >= 2) {
        		$('.calendarBody tbody td').removeClass('onSelect');
        	}
            sel.fadeOut(0, function() {
            	sel.addClass('onSelect'); //or any other class
            }).fadeIn(400);
        	onSelectList = $('.onSelect');
            if(onSelectList.length === 2) {
            	let onSelectedVal = $('.onSelect').map(function () {
            		  return $(this).attr("value");
            		});
            	onSelectedVal.sort();
    			$.ajax({
					url: urlTarget,
					type: "POST",
					data: {
						"action": "checkDateRange",
						"roomId": $('[name="roomId"]').val(),
						"dateFrom": onSelectedVal[0],
						"dateTo": onSelectedVal[1],
					},
					success: function(data) {
						var re = JSON.parse(data);
						let resultList = re.valid
						$('.calendarBody tbody td').removeClass('onSelect');
						if(resultList === false) {
							console.log('result = false')
							$('[name="checkInDate"]').val('');
							$('[name="checkOutDate"]').val('');
							$('#dayCount').html('');
						} else {
							resultList.forEach(element => $('td[value="' + element + '"]').addClass('orderedRange'))
							$('td[value="' + onSelectedVal[1] + '"]').addClass('orderedRange')
							$('[name="checkInDate"]').val(onSelectedVal[0]);
							$('[name="checkOutDate"]').val(onSelectedVal[1]);
							$('#dayCount').html(resultList.length);
						}
					}
    			});
            }            
        })

        $(document).on('click', '.lastMonth', function() {
        	let y = currentStart.getFullYear();
        	let m = currentStart.getMonth();
            if (m === 0) {
            	currentStart.setMonth(11);
            	currentStart.setYear(y-1);
            } else {
            	currentStart.setMonth(m-1);
            }
            delCal(new Date(currentStart.getFullYear(), currentStart.getMonth(), 1), drawCal)
        })

        $(document).on('click', '.nextMonth', function() {
        	let y = currentStart.getFullYear();
        	let m = currentStart.getMonth();
            if (m === 11) {
            	currentStart.setMonth(0);
            	currentStart.setYear(y+1);
            } else {
            	currentStart.setMonth(m+1);
            }
            delCal(new Date(currentStart.getFullYear(), currentStart.getMonth(), 1), drawCal)
            
        });
        
        $('.calendarBody tbody td[value="' + $('input[name="checkInDate"]').val() + '"]').click();
        $('.calendarBody tbody td[value="' + $('input[name="checkOutDate"]').val() + '"]').click();
		
	});