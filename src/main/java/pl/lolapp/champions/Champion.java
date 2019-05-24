package pl.lolapp.champions;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity(name = "champions")
public class Champion implements Serializable {


	private String id;
	private int key;
	private String name;
	private String title;
	@OneToOne
	private Image image;
	@OneToMany
	private List<Skin> skins;
	private String lore;
	private String blurb;
	@OneToMany
	private List<String> allytips;
	@OneToMany
	private List<String> enemytips;
	@OneToMany  ///zmienic pozniej na manymany
	private List<String> tags;
	private String partype;
	@OneToOne
	private Info info;
	@OneToOne
	private Stats stats;
	@OneToMany
	private List<ChampionSpell> spells;
	@OneToOne
	private Passive passive;
	@OneToMany
	private List<Recommended> recommended;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Skin> getSkins() {
		return skins;
	}

	public void setSkins(List<Skin> skins) {
		this.skins = skins;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public List<String> getAllytips() {
		return allytips;
	}

	public void setAllytips(List<String> allytips) {
		this.allytips = allytips;
	}

	public List<String> getEnemytips() {
		return enemytips;
	}

	public void setEnemytips(List<String> enemytips) {
		this.enemytips = enemytips;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getPartype() {
		return partype;
	}

	public void setPartype(String partype) {
		this.partype = partype;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public List<ChampionSpell> getSpells() {
		return spells;
	}

	public void setSpells(List<ChampionSpell> spells) {
		this.spells = spells;
	}

	public Passive getPassive() {
		return passive;
	}

	public void setPassive(Passive passive) {
		this.passive = passive;
	}

	public List<Recommended> getRecommended() {
		return recommended;
	}

	public void setRecommended(List<Recommended> recommended) {
		this.recommended = recommended;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}