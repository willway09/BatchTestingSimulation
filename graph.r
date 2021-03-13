library("rgl")
library("plot3D")
library("DBI")


con = dbConnect(RSQLite::SQLite(), "file/data.db")

data = dbReadTable(con, "MultiLevel")
dbDisconnect(con)

head(data)
nrow(data)

r = seq(0, 360, length.out=73)
system("cd img; rm *; cd ..")

for(i in r) {
	#png(file="out.png")
	png(file=sprintf("img/%d.png", i))
	scatter3D(data$n, data$m, data$mean, theta=i, phi=0)
	dev.off()

}

system("cd img; convert -delay 20 -loop 0 $(ls | sort -n) out.gif; cd ..")

#system("cd img; convert -delay 20 -loop 0 $(ls | sort -n) out.gif; cp out.gif ..; cd ..")



#data = read.csv("file/multi.csv", header = FALSE)
#data$r = 100 * data$r


#z = matrix(data=NA, nrow=length(unique(data$n)), ncol=length(unique(data$r)))
#
#length(unique(data$n))
#length(unique(data$r))
#
#for(i in 1:nrow(data)) {
	#z[(data$n)[i], (data$r)[i]] = (data$mean)[i];
#}
#
#x = unique(data$n)
#y = unique(data$r)
#x
#y
#
##head(x)
##head(y)
##head(z)
#
#system("rm out.gif; cd img; rm *; cd ..")
#r = seq(0, 360, length.out=73)
#
#for(i in r) {
	#png(file=sprintf("img/%d.png", i))
	##scatter3D(x, y, z, clab = c("Number of", "tests"), theta = i, phi = 0) 
	##scatter3D(x, y, z, colvar = NULL, col = "blue", pch = 19, cex = .5, theta = i, phi = 0, clab = c("Sepal", "Width", "Height"))
	#persp3D(x, y, z, theta = i, phi = 30)
#
#
#
	##segments3D(0,0,0,100,100,100, add = TRUE)
#
#
#
#
	#dev.off()
#}
#
#print("Creating gif")
#system("cd img; convert -delay 20 -loop 0 $(ls | sort -n) out.gif; cd ..")
#
