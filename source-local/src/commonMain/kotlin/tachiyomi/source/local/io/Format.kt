package tachiyomi.source.local.io

import com.hippo.unifile.UniFile
import tachiyomi.core.storage.extension
import tachiyomi.source.local.io.Archive.isSupported

sealed interface Format {
    data class Directory(val file: UniFile) : Format
    data class Archive(val file: UniFile) : Format
    data class Epub(val file: UniFile) : Format

    class UnknownFormatException : Exception()

    companion object {

        fun valueOf(file: UniFile) = when {
            file.isDirectory -> Directory(file)
            file.extension.equals("epub", true) -> Epub(file)
            isSupported(file) -> Archive(file)
            else -> throw UnknownFormatException()
        }
    }
}
