    /* Thymeleaf에서 전달받은 SalesChat 리스트 */
    var salesChats = [[${salesChats}]];

    /* 월별 판매액 데이터 추출 */
    var revenuePerMonth = salesChats.map(function(chat) {
        return chat.revenuePerMonth;
    });

    /* 날짜 데이터 추출 (예: "2023-01-01") */
    var orderDates = salesChats.map(function(chat) {
        return chat.orderDate;
    });

    /* 날짜 데이터를 월 형식 (예: "2023-01")로 변환 */
    var months = orderDates.map(function(date) {
        return date.substring(0, 7);
    });

    /* 막대바 차트 생성 */
    var ctx = document.getElementById('myBarChart').getContext('2d');
    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: months,
            datasets: [{
                label: 'Monthly Revenue',
                data: revenuePerMonth,
                backgroundColor: 'rgba(2, 117, 216, 1)'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });