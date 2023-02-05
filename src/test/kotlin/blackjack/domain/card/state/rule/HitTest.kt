package blackjack.domain.card.state.rule

import blackjack.ClubAce
import blackjack.ClubJack
import blackjack.ClubNine
import blackjack.ClubTen
import blackjack.ClubThree
import blackjack.ClubTwo
import blackjack.domain.card.PlayingCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `Bust(Ten + Jack + Two = 0)`() {
        // given
        val cards = PlayingCards(ClubTen, ClubJack)
        val hit = Hit(cards)

        // when
        val actual = hit.draw(ClubTwo)

        // then
        assertThat(actual).isInstanceOf(Bust::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(0)
    }

    @Test
    fun `Hit(Ace + Two + Three = 16`() {
        // given
        val cards = PlayingCards(ClubAce, ClubTwo)
        val hit = Hit(cards)

        // when
        val actual = hit.draw(ClubThree)

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(16)
    }

    @Test
    fun `Stay(Nine + Ten = 19)`() {
        // given
        val cards = PlayingCards(ClubNine, ClubTen)
        val hit = Hit(cards)

        // when
        val actual = hit.stay()

        // then
        assertThat(actual).isInstanceOf(Stay::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(19)
    }

    @Test
    fun `Soft(Ace + Ace = 12)`() {
        // given
        val cards = PlayingCards(ClubAce, ClubAce)

        // when
        val actual = Hit(cards)

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(12)
    }

    @Test
    fun `Soft(Ace + Ace + Ten = 12)`() {
        // given
        val cards = PlayingCards(ClubAce, ClubAce, ClubTen)

        // when
        val actual = Hit(cards)

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(12)
    }
}