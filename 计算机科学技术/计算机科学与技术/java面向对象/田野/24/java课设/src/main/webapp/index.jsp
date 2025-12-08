<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .book-list {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .book-list th, .book-list td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .book-list th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .book-list tr:hover {
            background-color: #f5f5f5;
        }
        .loading {
            text-align: center;
            padding: 20px;
            font-style: italic;
            color: #666;
        }
        .error {
            color: red;
            text-align: center;
            padding: 10px;
        }
        .refresh-btn {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .refresh-btn:hover {
            background-color: #45a049;
        }
        .search-container {
            text-align: center;
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .search-input {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 60%;
        }
        .search-btn {
            padding: 10px 15px;
            background-color: #008CBA;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .search-btn:hover {
            background-color: #007095;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
            gap: 10px;
            flex-wrap: wrap;
        }
        .add-btn {
            background-color: #ff9800;
        }
        .add-btn:hover {
            background-color: #e68a00;
        }
        .delete-btn {
            background-color: #f44336;
        }
        .delete-btn:hover {
            background-color: #d32f2f;
        }
        .action-btn {
            padding: 5px 10px;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.4);
        }
        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 340px;
            border-radius: 5px;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        .close:hover {
            color: black;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-submit {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        .form-submit:hover {
            background-color: #45a049;
        }
        .toast {
            visibility: hidden;
            min-width: 250px;
            background-color: #333;
            color: #fff;
            text-align: center;
            border-radius: 2px;
            padding: 16px;
            position: fixed;
            z-index: 1;
            left: 50%;
            bottom: 30px;
            transform: translateX(-50%);
        }
        .toast.show {
            visibility: visible;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }
        @keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }
        @keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

        /* disabled borrow button style */
        .disabled-btn {
            background-color: #cccccc !important;
            color: #666666 !important;
            cursor: not-allowed !important;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>图书列表</h1>

        <div class="search-container">
            <input type="text" id="searchInput" class="search-input" placeholder="输入书名搜索...">
            <button id="searchBtn" class="search-btn">搜索</button>
        </div>

        <div class="button-container">
            <button id="refreshBtn" class="refresh-btn">显示全部图书</button>
            <button id="addBookBtn" class="refresh-btn add-btn">添加图书</button>
            <button id="showStatsBtn" class="refresh-btn">统计</button>
            <button id="showRecordsBtn" class="refresh-btn">借阅记录</button>
        </div>

        <div id="loading" class="loading">正在加载图书数据...</div>
        <div id="error" class="error" style="display: none;"></div>

        <table id="bookTable" class="book-list" style="display: none;">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>书名</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="bookList">
                <!-- 图书数据将在这里动态加载 -->
            </tbody>
        </table>
    </div>

    <!-- 添加图书的模态框 -->
    <div id="addBookModal" class="modal">
        <div class="modal-content">
            <span class="close" data-close="addBookModal">&times;</span>
            <h2>添加新图书</h2>
            <div class="form-group">
                <label for="bookName">书名:</label>
                <input type="text" id="bookName" placeholder="请输入书名">
            </div>
            <button id="submitAddBook" class="form-submit">添加</button>
        </div>
    </div>

    <!-- 借阅模态框：不再需要手动输入ID，只填写用户名 -->
    <div id="borrowModal" class="modal">
        <div class="modal-content">
            <span class="close" data-close="borrowModal">&times;</span>
            <h2>借阅图书</h2>
            <div class="form-group">
                <label for="borrowUsername">用户名:</label>
                <input type="text" id="borrowUsername" placeholder="请输入用户名">
            </div>
            <button id="submitBorrow" class="form-submit">借阅</button>
        </div>
    </div>

    <!-- 归还模态框 -->
    <div id="returnModal" class="modal">
        <div class="modal-content">
            <span class="close" data-close="returnModal">&times;</span>
            <h2>归还图书</h2>
            <div class="form-group">
                <label for="returnBookId">书ID:</label>
                <input type="number" id="returnBookId" placeholder="请输入书ID">
            </div>
            <button id="submitReturn" class="form-submit">归还</button>
        </div>
    </div>

    <!-- 统计模态框 -->
    <div id="statsModal" class="modal">
        <div class="modal-content">
            <span class="close" data-close="statsModal">&times;</span>
            <h2>统计</h2>
            <div id="statsContent" style="font-size:16px; padding-top:10px;"></div>
        </div>
    </div>

    <!-- 借阅记录模态框 -->
    <div id="recordsModal" class="modal">
        <div class="modal-content" style="width:90%; max-width:1000px;">
            <span class="close" data-close="recordsModal">&times;</span>
            <h2>借阅记录</h2>
            <table id="recordsTable" class="book-list" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>书ID</th>
                        <th>用户名</th>
                        <th>借出</th>
                        <th>到期</th>
                        <th>归还</th>
                        <th>状态</th>
                    </tr>
                </thead>
                <tbody id="recordsBody"></tbody>
            </table>
        </div>
    </div>

    <!-- 提示消息 -->
    <div id="toast" class="toast"></div>

    <script>
        // 当前待借阅的书ID（由列表上的借阅按钮设置）
        var currentBorrowBookId = null;
        // 存放当前已被借出的书ID集合（由 /bookrecords 返回的 status=0 的记录决定）
        var borrowedBookIds = new Set();

        // 页面加载完成后执行
        document.addEventListener('DOMContentLoaded', function() {
            // 首先获取当前被借出的书ID集合，然后加载图书
            fetchBorrowedSet().then(function(){
                loadBooks();
            }).catch(function(){
                // 即便失败也继续加载图书（不阻塞基本功能）
                loadBooks();
            });

            // 按钮事件绑定
            document.getElementById('refreshBtn').addEventListener('click', function() { document.getElementById('searchInput').value = ''; refreshBorrowedAndBooks(); });
            document.getElementById('searchBtn').addEventListener('click', searchBooks);
            document.getElementById('searchInput').addEventListener('keypress', function(e){ if (e.key === 'Enter') searchBooks(); });

            document.getElementById('addBookBtn').addEventListener('click', function(){ openModal('addBookModal'); });
            document.getElementById('showStatsBtn').addEventListener('click', showStats);
            document.getElementById('showRecordsBtn').addEventListener('click', showRecords);

            // Modal close elements
            document.querySelectorAll('.close').forEach(function(el){
                el.addEventListener('click', function(){ closeModal(el.getAttribute('data-close')); });
            });
            window.addEventListener('click', function(event){
                // reserveModal removed from list
                ['addBookModal','borrowModal','returnModal','statsModal','recordsModal'].forEach(function(id){
                    if (event.target == document.getElementById(id)) closeModal(id);
                });
            });

            // Form submit handlers
            document.getElementById('submitAddBook').addEventListener('click', submitAddBook);
            document.getElementById('submitBorrow').addEventListener('click', submitBorrow);
            document.getElementById('submitReturn').addEventListener('click', submitReturn);
            // reservation removed
        });

        function openModal(id){ document.getElementById(id).style.display = 'block'; }
        function closeModal(id){ document.getElementById(id).style.display = 'none'; }

        function showToast(msg){
            var t = document.getElementById('toast');
            t.textContent = msg;
            t.className = 'toast show';
            setTimeout(function(){ t.className = 'toast'; }, 3000);
        }

        // Fetch the current borrowed book IDs from /bookrecords and populate borrowedBookIds set
        function fetchBorrowedSet(){
            return fetch('bookrecords')
                .then(function(response){
                    if (!response.ok) throw new Error('无法获取借阅记录，状态码:' + response.status);
                    return response.json();
                })
                .then(function(records){
                    borrowedBookIds.clear();
                    if (records && records.length) {
                        records.forEach(function(r){
                            // treat status==0 as currently borrowed; also treat status '0' string
                            try {
                                var status = r.status;
                                var bid = r.bookid || r.bookId || r.bookId || r.bookID;
                                // normalize bid
                                if (typeof bid === 'string' && /^\d+$/.test(bid)) bid = parseInt(bid, 10);
                                if (status === 0 || status === '0') {
                                    if (bid != null) borrowedBookIds.add(Number(bid));
                                }
                            } catch(e){ /* ignore malformed record */ }
                        });
                    }
                })
                .catch(function(err){
                    console.error('fetchBorrowedSet error:', err);
                    // clear set on error to avoid stale disabling
                    borrowedBookIds.clear();
                    throw err;
                });
        }

        // helper to refresh borrowed set then books
        function refreshBorrowedAndBooks(){
            fetchBorrowedSet().then(function(){ loadBooks(); }).catch(function(){ loadBooks(); });
        }

        // 搜索图书
        function searchBooks(){ const searchTerm = document.getElementById('searchInput').value.trim(); loadBooks(searchTerm); }

        // 加载图书数据
        function loadBooks(searchTerm){
            document.getElementById('loading').style.display = 'block';
            document.getElementById('error').style.display = 'none';
            document.getElementById('bookTable').style.display = 'none';

            let url = 'bookservice';
            if (searchTerm) url += '?name=' + encodeURIComponent(searchTerm);

            fetch(url)
                .then(response => { if (!response.ok) throw new Error('网络请求失败，状态码: ' + response.status); return response.json(); })
                .then(books => {
                    document.getElementById('loading').style.display = 'none';
                    document.getElementById('bookTable').style.display = 'table';
                    const bookList = document.getElementById('bookList');
                    bookList.innerHTML = '';

                    if (books && books.length > 0) {
                        books.forEach(book => {
                            const row = document.createElement('tr');
                            const idCell = document.createElement('td'); idCell.textContent = book.bookid; row.appendChild(idCell);
                            const nameCell = document.createElement('td'); nameCell.textContent = book.bookname; row.appendChild(nameCell);

                            const actionCell = document.createElement('td');

                            // Borrow button - if book is currently borrowed, disable it and style as grey
                            const borrowBtn = document.createElement('button');
                            borrowBtn.textContent = '借阅';
                            borrowBtn.className = 'action-btn refresh-btn';

                            var isBorrowed = borrowedBookIds.has(Number(book.bookid));
                            if (isBorrowed) {
                                borrowBtn.disabled = true;
                                borrowBtn.className = 'action-btn refresh-btn disabled-btn';
                                borrowBtn.title = '该书已被借出';
                            } else {
                                borrowBtn.onclick = function(){ currentBorrowBookId = book.bookid; document.getElementById('borrowUsername').value = ''; openModal('borrowModal'); };
                            }
                            actionCell.appendChild(borrowBtn);

                            // Return button
                            const returnBtn = document.createElement('button');
                            returnBtn.textContent = '归还';
                            returnBtn.className = 'action-btn delete-btn';
                            returnBtn.onclick = function(){ document.getElementById('returnBookId').value = book.bookid; openModal('returnModal'); };
                            actionCell.appendChild(returnBtn);

                            // Delete button
                            const deleteBtn = document.createElement('button');
                            deleteBtn.textContent = '删除';
                            deleteBtn.className = 'action-btn delete-btn';
                            deleteBtn.onclick = function(){ removeBook(book.bookid); };
                            actionCell.appendChild(deleteBtn);

                            row.appendChild(actionCell);
                            bookList.appendChild(row);
                        });
                    } else {
                        const row = document.createElement('tr');
                        const cell = document.createElement('td'); cell.colSpan = 3; cell.textContent = searchTerm ? '没有找到包含"'+searchTerm+'"的图书' : '没有找到图书数据'; cell.style.textAlign = 'center'; row.appendChild(cell); bookList.appendChild(row);
                    }
                })
                .catch(error => { document.getElementById('loading').style.display = 'none'; const errorElem = document.getElementById('error'); errorElem.textContent = '加载图书数据失败: ' + error.message; errorElem.style.display = 'block'; console.error('Error:', error); });
        }

        // 添加图书
        function submitAddBook(){
            const bookName = document.getElementById('bookName').value.trim();
            if (!bookName) { showToast('请输入图书名称'); return; }
            fetch('bookadd?name=' + encodeURIComponent(bookName))
                .then(response => { if (!response.ok) return response.json().then(r=>{throw new Error(r.message||('状态码:'+response.status))}); return response.json(); })
                .then(res => { closeModal('addBookModal'); document.getElementById('bookName').value = ''; showToast(res.message||'添加成功'); // refresh borrowed set and list
                    refreshBorrowedAndBooks();
                })
                .catch(err => { showToast('添加失败: ' + err.message); console.error(err); });
        }

        // 借阅操作（不需要手动输入ID）
        function submitBorrow(){
            const id = currentBorrowBookId;
            const user = document.getElementById('borrowUsername').value.trim();
            if (!id || !user) { showToast('请选择图书并输入用户名'); return; }
            fetch('bookborrow?bookid=' + encodeURIComponent(id) + '&username=' + encodeURIComponent(user))
                .then(response => { return response.json().then(r => { if (!response.ok) throw new Error(r.message||'请求失败'); return r; }); })
                .then(res => {
                    closeModal('borrowModal');
                    showToast(res.message || (res.success? '借阅成功':'借阅失败'));
                    currentBorrowBookId = null;
                    // update borrowed set and reload list so buttons reflect new state
                    refreshBorrowedAndBooks();
                })
                .catch(err => { showToast('借阅失败: ' + err.message); console.error(err); });
        }

        // 归还操作
        function submitReturn(){
            const id = document.getElementById('returnBookId').value;
            if (!id) { showToast('请输入书ID'); return; }
            fetch('bookreturn?bookid=' + encodeURIComponent(id))
                .then(response => { return response.json().then(r => { if (!response.ok) throw new Error(r.message||'请求失败'); return r; }); })
                .then(res => { closeModal('returnModal'); showToast(res.message || (res.success? '归还成功':'归还失败')); // refresh borrowed set and list
                    refreshBorrowedAndBooks();
                })
                .catch(err => { showToast('归还失败: ' + err.message); console.error(err); });
        }

        // small helper to pad numbers for date formatting
        function pad(n){ return n < 10 ? ('0' + n) : n; }

        // format various date inputs (millis, /Date(123)/, ISO strings) into readable local datetime
        function formatDate(val){
            if (val === null || val === undefined || val === '') return '';
            var ms = null;
            if (typeof val === 'number') ms = val;
            else if (typeof val === 'string'){
                var m = val.match(/\((\d+)\)/);
                if (m) ms = parseInt(m[1],10);
                else if (/^\d+$/.test(val)) ms = parseInt(val,10);
                else {
                    var d = Date.parse(val);
                    if (!isNaN(d)) ms = d;
                }
            } else if (val instanceof Date) ms = val.getTime();
            if (!ms) return '';
            var d = new Date(ms);
            return d.getFullYear() + '-' + pad(d.getMonth()+1) + '-' + pad(d.getDate()) + ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds());
        }

        // 删除图书
        function removeBook(id){
            if (!confirm('确定删除该图书吗？')) return;
            fetch('bookremove?searchID=' + encodeURIComponent(id))
                .then(response => { return response.json().then(r => { if (!response.ok) throw new Error(r.message||'请求失败'); return r; }); })
                .then(res => { showToast(res.message || (res.success? '删除成功':'删除失败')); // refresh borrowed set and list
                    refreshBorrowedAndBooks();
                })
                .catch(err => { showToast('删除失败: ' + err.message); console.error(err); });
        }

        // 查看统计
        function showStats(){
            fetch('bookstats')
                .then(response => { if (!response.ok) throw new Error('统计请求失败，状态码: ' + response.status); return response.json(); })
                .then(data => {
                    // populate stats modal and open it
                    var s = document.getElementById('statsContent');
                    s.innerHTML = '<strong>总书数：</strong>' + (data.total!=null?data.total:'-') + '<br/>' +
                                  '<strong>已借出：</strong>' + (data.borrowed!=null?data.borrowed:'-') + '<br/>' +
                                  '<strong>逾期：</strong>' + (data.overdue!=null?data.overdue:'-');
                    openModal('statsModal');
                })
                .catch(err => { showToast('统计失败: ' + err.message); console.error(err); });
        }

        // 查看借阅记录（在模态框中以表格展示）
        function showRecords(){
            // helper: extract array from common wrapper shapes
            function extractRecords(json){
                if (!json) return [];
                if (Array.isArray(json)) return json;
                if (Array.isArray(json.records)) return json.records;
                if (Array.isArray(json.data)) return json.data;
                if (Array.isArray(json.result)) return json.result;
                // sometimes API returns { success: true, data: [...] }
                for (var k in json){ if (Array.isArray(json[k])) return json[k]; }
                return [];
            }

            // helper: convert various date representations to millis (number) or null
            function toMillis(val){
                if (!val && val !== 0) return null;
                if (typeof val === 'number') return val;
                if (typeof val === 'string'){
                    // handle /Date(1234567890)/
                    var m = val.match(/\((\d+)\)/);
                    if (m) return parseInt(m[1],10);
                    // plain digits
                    if (/^\d+$/.test(val)) return parseInt(val,10);
                    var d = Date.parse(val);
                    if (!isNaN(d)) return d;
                    return null;
                }
                try { var d = new Date(val); return isNaN(d.getTime())?null:d.getTime(); } catch(e){ return null; }
            }

            fetch('bookrecords')
                .then(function(response){
                    if (!response.ok) throw new Error('借阅记录请求失败，状态码: ' + response.status);
                    return response.json();
                })
                .then(function(json){
                    var records = extractRecords(json);

                    // If empty but json itself looks like a single record, try to wrap
                    if ((!records || records.length === 0) && json && typeof json === 'object' && json.id) records = [json];

                    // Normalize and sort by borrow date desc
                    records = records.map(function(r){
                        var borrowMs = toMillis(r.borrowDate || r.borrow_date);
                        var dueMs = toMillis(r.dueDate || r.due_date);
                        var returnMs = toMillis(r.returnDate || r.return_date);
                        return Object.assign({}, r, {__borrowMs: borrowMs, __dueMs: dueMs, __returnMs: returnMs});
                    });
                    records.sort(function(a,b){ return (b.__borrowMs || 0) - (a.__borrowMs || 0); });

                    // Update borrowedBookIds from active records (status == 0)
                    try {
                        borrowedBookIds.clear();
                        records.forEach(function(r){ var status = r.status; if (status === 0 || status === '0') { var bid = r.bookid || r.bookId || r.bookID; if (bid != null) borrowedBookIds.add(Number(bid)); } });
                    } catch(e){ /* ignore */ }

                    // Render table
                    const body = document.getElementById('recordsBody');
                    body.innerHTML = '';
                    if (records && records.length>0) {
                        records.forEach(function(r){
                            var st = (r.status==0? '借出' : (r.status==1? '已归还' : '逾期'));
                            var tr = document.createElement('tr');
                            tr.innerHTML = '<td>' + (r.id || '') + '</td>' +
                                           '<td>' + (r.bookid || r.bookId || '') + '</td>' +
                                           '<td>' + (r.username || r.user || '') + '</td>' +
                                           '<td>' + formatDate(r.__borrowMs || r.borrowDate || r.borrow_date) + '</td>' +
                                           '<td>' + formatDate(r.__dueMs || r.dueDate || r.due_date) + '</td>' +
                                           '<td>' + formatDate(r.__returnMs || r.returnDate || r.return_date) + '</td>' +
                                           '<td>' + st + '</td>';
                            body.appendChild(tr);
                        });
                    } else {
                        const tr = document.createElement('tr');
                        const td = document.createElement('td'); td.colSpan = 7; td.style.textAlign = 'center'; td.textContent = '没有借阅记录'; tr.appendChild(td); body.appendChild(tr);
                    }

                    // Open records modal
                    openModal('recordsModal');
                })
                .catch(function(err){
                    showToast('获取借阅记录失败: ' + err.message);
                    console.error(err);
                });
        }

    </script>
</body>
</html>
