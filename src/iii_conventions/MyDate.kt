package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
    operator fun contains(item: MyDate): Boolean = start <= item && item <= endInclusive
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var startDate: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = startDate
        startDate = startDate.nextDay()
        return result
    }
    override fun hasNext(): Boolean = startDate <= dateRange.endInclusive
}
