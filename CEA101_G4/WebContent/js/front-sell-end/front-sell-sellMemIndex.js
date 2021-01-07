            function isLeapYear(year) {
                // 西元年份除以4不可整除，為平年。
                // 西元年份除以4可整除，且除以100不可整除，為閏年。
                // 西元年份除以100可整除，且除以400不可整除，為平年
                // 西元年份除以400可整除，為閏年。
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

            let delCal = function (firstDateOfMonth, callback) {
                $('tbody').html('')
                callback(firstDateOfMonth)
            }

            Date.prototype.yyyymmdd = function () {
                var mm = this.getMonth() + 1 // getMonth is zero-based
                var dd = this.getDate()

                return [this.getFullYear(), (mm > 9 ? '' : '0') + mm, (dd > 9 ? '' : '0') + dd].join('-')
            }

            let isPassedDate = function (compareDate) {
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
            
            let isCurrentDate = function (compareDate) {
                let today = new Date()
                if (compareDate.getFullYear() == today.getFullYear()
                		&& compareDate.getMonth() == today.getMonth()
                		&& compareDate.getDate() == today.getDate()) {
                    return true
                }

                return false
            }

            let drawCal = function (firstDateOfMonth) {
                let monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
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

                let tb = document.getElementsByTagName('tbody')[0]
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
                            a.setAttribute('href', '#')
                            if (isPassedDate(setDate)) {
                            	td.classList.add('passedDate')
                            } else if (isCurrentDate(setDate)) {
                            	td.classList.add('currentDate');
                            	td.classList.add('onSelect');
                            }
                        }
                        td.appendChild(a)
                        tr.appendChild(td)
                        i++
                    }
                    tb.appendChild(tr)
                }
            }

            $(document).ready(function () {
            	var contextPath = $('[name="contextPath"]').val();
            	var urlTarget = contextPath + '/front-sell-end/toolforms/collapsibleGroup.jsp';
            	console.log('url = ' + urlTarget);
            	
                let monthList = document.getElementById('monthList')
                let changeMonth = function () {
                    delCal(new Date(yearList.value, monthList.value, 1), drawCal)
                }

                $('#yearList').change(changeMonth)
                monthList.addEventListener('change', changeMonth)
                let today = new Date()

                for (let i = 1971; i <= 2050; i++) {
                    let option = document.createElement('option')
                    yearList.appendChild(option)
                    option.value = i
                    option.innerText = i
                    if (i === today.getFullYear()) option.selected = true
                }

                let monOptions = monthList.getElementsByTagName('option')
                for (let i = 0; i < monOptions.length; i++) {
                    if (monOptions[i].value === today.getMonth().toString()) monOptions[i].selected = true
                }
                drawCal(new Date(today.getFullYear(), today.getMonth(), 1))
                
                
                $(document).on('click', '.calendarBody td', function() {
                	console.log($(this).attr('value'));
                	var sel = $(this);
                	$('.calendarBody td').removeClass('onSelect');
//                	sel.stop(true, true).addClass('onSelect', 3000);
                	sel.fadeOut(0, function(){
                	    $(this).addClass('onSelect'); //or any other class
                	}).fadeIn(500);
                	
                	$.ajax({
                		url: urlTarget,
                		type: 'POST', 
                		data: {
                			'sellMemId' : $('#sellMemId').val(),
                			'checkInDate': sel.attr('value'),
                		},
                		success: function(data){
//                			console.log(data);
                			$('#accordion').stop(true, true).fadeOut(100, function() {
                				$('.displaySelectedDate h4').html(sel.attr('value') + '  預計Check in 列表');
                				$(this).html(data);
                			}).fadeIn(500);
                		}
                	})
                })
                
                
            })