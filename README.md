# tutorial-2:

Code quality issue:<br>
<ol>
<li>Tidak menggunakan public method -> ubah public method menjadi default method</li>
<li>Duplicate code -> buat sebuah fungsi yang bisa dipanggil berkali-kali</li>
<li>Meningkatkan maintainability -> hindari penggunaan literal yang sama, ganti dengan variable/contants</li>
</ol>

Workflow sudah memenuhi apa itu CI/CD, karena:<br>
<ol>
<li>Workflow mengharuskan untuk menjalankan semua unit test melalui perintah ./gradlew test</li>
<li>Workflow mengharuskan juga melakukan code scanning menggunakan sonarcloud agar menghindari code smells</li>
<li>Membuat executeable build secara otomatis</li>
<li>Automatic deploy ke Koyeb setiap terjadi push pada main branch</li>
</ol>