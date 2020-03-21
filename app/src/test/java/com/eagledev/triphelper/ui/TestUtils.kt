package com.eagledev.triphelper.ui

import androidx.paging.PagedList
import com.eagledev.triphelper.model.TripInfo
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset

fun getOffsetDateTime(): OffsetDateTime = OffsetDateTime.of(
        LocalDateTime.of(2019, 5, 12, 5, 45),
        ZoneOffset.ofHoursMinutes(6, 0))

fun getTripInfo() =
    TripInfo(3, 50)

/**
 * Generate a mock of live data of any type for testing
 */
@Suppress("UNCHECKED_CAST")
fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList
}