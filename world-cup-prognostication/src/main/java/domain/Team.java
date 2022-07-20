package domain;

import java.util.Objects;

public class Team implements Comparable<Team> {
	private final Country name;
	private       int     rating;

	public Team(Country name, int rating) {
		this.name = name;
		this.rating = rating;
	}

	public Country getName() {
		return name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Team)) {
			return false;
		}

		Team team = (Team) obj;
		return getRating() == team.getRating() && getName() == team.getName();
	}

	@Override
	public int compareTo(Team team) {
		return Integer.compare(getRating(), team.getRating());
	}

	@Override
	public String toString() {
		return String.format("%s : %d", getName(), getRating());
	}
}
