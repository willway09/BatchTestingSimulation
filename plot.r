createGraph = function() {
	data=read.csv("data.csv", header=TRUE);

	png(filename="comparison.png",width=800,height=800)

	head(data);
	plot.new()
	title(main="Maximum Fraction of Tests Saved for Various Infection Rates",xlab="Infection Rate",ylab="Fraction of Tests Saved");
	#plot(data$p,data$Multi.Two.Level,pch=23,col="chartreuse4",bg="chartreuse4",xlab="Infection Rate",ylab="Fraction of Tests Saved");
	axis(side=1,at=seq(0,1,by=.1))
	axis(side=2,at=seq(-.1,1,by=.1))


	points(data$p,data$Multi.Two.Level,pch=23,col="chartreuse4",bg="chartreuse4")
	points(data$p,data$Multi.Level,pch=22,col="blue",bg="blue")
	points(data$p,data$Two.Level,pch=21,col="red",bg="red")

	print(head(data$Two.Level))
	print(head(data$Multi.Level))

	legend(x="topright", 
	       legend=c("Two Level", "Multi Level", "Multi Two Level"),
	       pch=c(21,22,23),
	       col=c("red","blue","chartreuse4"),
	       pt.bg=c("red","blue","chartreuse4")

	)

	dev.off();
}

src = function() {
	source("plot.r")
}
