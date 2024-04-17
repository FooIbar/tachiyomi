package eu.kanade.tachiyomi.data.coil

import coil3.intercept.Interceptor
import coil3.request.ImageResult
import tachiyomi.core.util.lang.withIOContext
import java.io.InputStream
import java.nio.ByteBuffer

/**
 * Wrap [InputStream] into [ByteBuffer].
 */
object InputStreamInterceptor : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val request = chain.request
        val data = request.data
        return if (data is InputStream) {
            val newData = withIOContext { ByteBuffer.wrap(data.readBytes()) }
            val newRequest = request.newBuilder().data(newData).build()
            chain.withRequest(newRequest).proceed()
        } else {
            chain.proceed()
        }
    }
}
