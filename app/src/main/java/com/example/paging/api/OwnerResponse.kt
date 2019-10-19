package com.example.paging.api

class OwnerResponse(
        var items: List<Item>,
        var has_more: Boolean
)

data class Item(
        var owner: Owner
)