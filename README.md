# refleksi tutorial-3:

Principles which i applied to my project:<br>
<ol>
<li>Single Responsibility Principle. Idenya adalah setiap kelas, modul, atau fungsi dalam program harus punya 1 responsibility saja. SRP saya terapkan pada project saya dengan memisahkan controller car dari controller product menjadi kelas independent. Hal ini saya lakukan karena method & variable pada car controller tidak diharapkan/digunakan pada car controller.</li>
<li>Open-Closed Principle. Prinsip ini menyatakan bahwa entities harus terbuka untuk ekstensi, tetapi tertutup untuk modifikasi. Penerapannya yaitu adanya interface yang nantinya dapat diimplementasi oleh banyak service kedepannya, agar perubahan hanya pada class implementasinya saja.</li>
<li>Liskov Subtitution Principle. LSP implies bahwa jika kelas di extend ke kelas lain, maka inheriting class harus berkelakuan sama / menggunakan seluruh properties dan behavior dari inherited class. LSP saya terapkan di project ini dengan membuat interface untuk masing-masing service yang cocok. Masing-masing service membutuhkan method yang berbeda-beda sehingga membutuhkan interface yang berbeda juga.</li>
</ol>

The advantages of applying SOLID principle:<br>
<ol>
<li>Class menjadi ringkas. Contoh: car controller sekarang tidak memuat method/var dari product controller lagi.</li>
<li>Mengurangi dependensi. Contohnya, kita dapat mengubah kode di class product controller tanpa mengakibatkan side effect di car controller.</li>
<li>Kode mudah dipahami. Contoh: jika ingin mengedit controller untuk car langsung saja buka file CarController.java, tidak perlu mencari classnya dulu di file ProductController.java.</li>
</ol>

The disadvantages of not applying SOLID principle:<br>
<ol>
<li>Class menjadi rumit. Contoh: car controller menginherit method/var dari product controller yang sama sekali tidak dibutuhkan.</li>
<li>Dependensi tinggi. Contohnya, mengubah kode di class product controller akan mengakibatkan side effect di car controller.</li>
<li>Kode sulit dipahami. Contoh: jika ingin mengedit controller untuk car, harus mencari classnya dulu di file ProductController.java.</li>
</ol>