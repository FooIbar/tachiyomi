package eu.kanade.tachiyomi.data.coil

import android.view.View
import coil3.size.Dimension
import coil3.size.Size
import coil3.size.ViewSizeResolver

class WebtoonViewSizeResolver<T : View>(override val view: T) : ViewSizeResolver<T> {
    override suspend fun size(): Size {
        val width = super.size().width as Dimension.Pixels
        return Size(width.px * 2, Dimension.Undefined)
    }
}
