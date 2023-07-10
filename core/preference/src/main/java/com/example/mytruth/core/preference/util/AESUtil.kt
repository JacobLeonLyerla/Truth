package com.example.mytruth.core.preference.util

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
// This is a utility class for AES encryption and decryption. It's defined as an object,
// which means it's a singleton; you can't create instances of AESUtil, and its methods and
// properties are all static.
internal object AESUtil {
    // We're using the AES (Advanced Encryption Standard) encryption algorithm.
    private const val ALGORITHM = "AES"
    // The transformation determines how data is encrypted and decrypted. In this case, we're
    // simply using AES without any mode or padding scheme.
    private const val TRANSFORMATION = "AES"

    // This function encrypts a string using the AES algorithm.
    fun encrypt(value: String, secretKey: String): String {
        // The SecretKeySpec class represents a secret (symmetric) key in a provider-independent fashion.
        // We're creating a new key spec with the provided secret key (converted to bytes) and the AES algorithm.
        val key = SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        // Cipher is the class used for encryption and decryption. We get an instance configured to use the
        // AES transformation.
        val cipher = Cipher.getInstance(TRANSFORMATION)
        // We initialize the cipher in encrypt mode with the secret key.
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // We encrypt the provided value (converted to bytes) and get back an array of bytes.
        val encryptedValue = cipher.doFinal(value.toByteArray())
        // The encrypted bytes aren't safe to use in a string as-is, so we encode them using Base64 and return the result.
        return Base64.getEncoder().encodeToString(encryptedValue)
    }

    // This function decrypts a previously encrypted string using the AES algorithm.
    fun decrypt(value: String, secretKey: String): String {
        // As in the encrypt function, we're creating a SecretKeySpec with the provided secret key and the AES algorithm.
        val key = SecretKeySpec(secretKey.toByteArray(), ALGORITHM)
        // We get a Cipher instance configured to use the AES transformation.
        val cipher = Cipher.getInstance(TRANSFORMATION)
        // We initialize the cipher in decrypt mode with the secret key.
        cipher.init(Cipher.DECRYPT_MODE, key)

        // The provided value is a Base64-encoded string, so we first decode it back into bytes.
        val decodedValue = Base64.getDecoder().decode(value)
        // We decrypt the bytes and get back an array of bytes.
        val decryptedValue = cipher.doFinal(decodedValue)
        // We convert the decrypted bytes back into a string and return it.
        return String(decryptedValue)
    }
}
