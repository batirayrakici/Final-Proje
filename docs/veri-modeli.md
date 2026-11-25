# Veri modeli

| Koleksiyon / tablo | Alanlar | Kim erişir |
|---|---|---|
| users (Firestore) | uid, bolumId, altDonem, anonimVarsayilan | Yalnızca kendisi |
| courses | courseId, kod, ad, bolumId | Aynı bölümün giriş yapmış kullanıcıları okur; yazma yok |
| offerings (ders kartı) | offeringId, courseId, hocaAdi, donem | Aynı bölüm okur |
| notes | noteId, offeringId, ownerUid, harfNotu, harfNotuBeyan=true, baslik, dosyaYolu, sayfaSayisi (<=30), hedefAltDonem, anonim, olusturmaZamani | Okuma: aynı bölüm + hedefAltDonem; yazma/silme: sahibi |
| Storage: notes/{noteId}.pdf | PDF | Notun okunma kuralıyla aynı |
| Room: CachedNote, CachedOffering, DraftNote | son bakılan kartlar, ders-hoca-dönem listesi, yarım paylaşım taslağı | Yalnızca cihazda |

Paylaşan adı `notes` belgesinde tutulmaz; `ownerUid` yalnızca silme yetkisi içindir.

## Mimari seçimi: Firebase + Room
Firebase paylaşımı ve girişi hazır çözer; kendi REST sunucusu 6 haftaya sığmaz; yalnızca Room kullanıcılar arası paylaşımı çözmez ama offline/taslak için yerel katman olarak kullanılır.
