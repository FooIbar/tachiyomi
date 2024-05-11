package eu.kanade.domain.extension.interactor

import eu.kanade.domain.source.service.SourcePreferences
import tachiyomi.core.preference.getAndSet

class TrustExtension(
    private val preferences: SourcePreferences,
) {

    fun isTrusted(signatureHash: String): Boolean {
        return signatureHash in preferences.trustedExtensions().get()
    }

    fun trust(signatureHash: String) {
        preferences.trustedExtensions().getAndSet { exts ->
            exts + signatureHash
        }
    }

    fun revokeAll() {
        preferences.trustedExtensions().delete()
    }
}
