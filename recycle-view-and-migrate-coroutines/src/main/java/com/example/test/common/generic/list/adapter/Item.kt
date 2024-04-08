package com.example.test.common.generic.list.adapter

abstract class Item {
    /**
     * The type of the Item. A defined id is preferred.
     */
    abstract val type: Int

    /**
     * The identifier of this item
     */
    abstract val identifier: Long

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as? Item?
        return identifier == that?.identifier
    }

    override fun hashCode(): Int {
        return identifier.hashCode()
    }
}