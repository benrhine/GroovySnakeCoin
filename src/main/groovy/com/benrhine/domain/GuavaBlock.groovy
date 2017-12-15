package com.benrhine.domain

import com.google.common.hash.Hashing

import java.nio.charset.StandardCharsets

/** =================================================================================================================
 * Interchangeable hashing implementation. (Guava)
 *
 * This block implementation does hashing using Google's Guava library as it is a bit more succinct.
 * ================================================================================================================== */
class GuavaBlock {
    Integer index
    String timestamp
    String data
    String previousHash
    String hash = this.hashBlock()

    GuavaBlock() { /* Default Constructor Unused */}

    GuavaBlock(final Integer index, final String timestamp, final String data, final String previousHash) {
        this.index = index
        this.timestamp = timestamp
        this.data = data
        this.previousHash = previousHash
        this.hash = this.hashBlock()
    }

    String hashBlock() {
        Hashing.sha256().hashString(this.index.toString() + this.timestamp + this.data + this.previousHash, StandardCharsets.UTF_8).toString()
    }
}
