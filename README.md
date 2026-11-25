# NotKöprüsü

Alt sınıfın, bir dersin hangi hocadan nasıl işlendiğini ve o dersi yüksek notla veren üst sınıfın düzenli notunu bulabildiği mobil uygulama (Android).

## Problem
Üst sınıf notları WhatsApp ve ortak klasörlerde karışık durur. Hangi dosyanın hangi derse, hangi hocaya ve hangi döneme ait olduğu yazılmaz. Alt sınıf, dersi yüksek notla veren kişinin özetini ararken ya bulamaz ya da yanlış hocanın notunu çalışır.

NotKöprüsü notu derse böler. Her kayıtta **hoca, dönem ve paylaşanın o dersten aldığı harf notu** görünür (harf notu "beyan"dır, doğrulanmamıştır).

## Hedef kullanıcı
Aynı bölümdeki alt sınıf öğrencileri ve notunu bırakmak isteyen üst sınıflar.

## Tasarım kararları
- Beğeni ve sohbet yok; güvenilirlik harf notuyla gösterilir.
- Kartta üç zorunlu alan: ders, hoca + dönem, alınan harf notu.
- Dosya yalnızca aynı bölümün ilgili alt dönemine açıktır; paylaşan adı gizlenebilir.

## Durum (Hafta 2)
- Figma ekran akışı (6 ekran): https://www.figma.com/design/Gb0CQYf8ovHvzMUQ5ed07M
- Mimari: Kotlin + Jetpack Compose → ViewModel → Repository → Room (yerel) / Firebase (uzak). Ayrıntı: `docs/mimari-diyagram.png`, `docs/veri-modeli.md`
- Açılış ekranı: `MainActivity.kt` içinde Compose taslağı; Android Studio'da çalıştırılıp doğrulanacak.
- Kişisel kısıt: henüz atanmadı.

## AI kullanımı
Tüm AI kullanımı `docs/ai-gunlugu.md` dosyasında kayıtlıdır.
