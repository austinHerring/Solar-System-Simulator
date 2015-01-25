# Solar-System-Simulator
Models the planets' radii as well as their orbit speeds, orbit radii and orbit eccentricities, keep everything proportional to one another.

This was the first project that I decided to do for fun on my own accord. I think it went pretty well considering I've only taken one Intro to Java Class. However, there were many things that I would have liked to add but think will need more experience to do so.

Some Problems and Considerations:
1) A Moon - It would've been really cool to add eath's moon but I quickly encountered the problem of updating the moon's path in each frame of the animation. I do not know how to do this update without redrawing all of the other planet orbits and restarting their trajectory. Therefore, I left it out.
2) The Sun - As of now, my Solar System's sun is represented as a yellow dot in the center of the Group...not very bright. Being that the sun's radius is 109.125 times the size of Earth's and 9.734 times the size of Jupiter's, including the sun with the proper proportions would make all the planets and their orbits negligibly small in the animation and pointless to view. I could possibly be able tom implement a correct sun size if I had a much better zoom feature.
3) Zoom In/Out - My current zoom feature doesn't really "zoom." It actually redraws the planets and their orbits whenever a new scope is selected. As a result the planets restart their animation when I don't really want them to. Its not that big of deal but it would be nice to fix. To be honest I have no clue on how to fix this right now.
4) Change of Rate - Changing the rate would update the instance variable, speed, that was used as a multiplier in the parts of the code where I set the duration. The problem came when I didn't want to restart the animation everytime the speed was updated. As of now it restarts and I don't have clue on how to fix that.
5) Real Time motion - At first I included a Real Time motion button, thinking it would be cool, but quickly found it to be uninteresting. They moved at such a slow rate that it was inapparent to the human eye. 

Overall, I'm proud of what I have done with the skills I have at this point in time. I tried to be as accurate as I could in the areas I could.
